package team.foobar.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.foobar.domain.Member;
import team.foobar.dto.Responser;
import team.foobar.dto.auth.LoginDto;
import team.foobar.dto.auth.TokenDto;
import team.foobar.dto.member.MemberDto;
import team.foobar.exception.AuthFailException;
import team.foobar.exception.ObjectNotFoundException;
import team.foobar.service.member.MemberService;
import team.foobar.util.JwtManager;

import java.util.HashMap;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/auth")
public class AuthController {
    private final MemberService memberService;
    private final MessageSource ms;
    private final PasswordEncoder encoder;
    private final JwtManager jwtManager;

    @PostMapping("/login")
    public Responser<Object> login(@Validated @RequestBody LoginDto dto, BindingResult br) throws Exception {
        Responser<Object> res = new Responser<>();
        if(br.hasFieldErrors()) {
            for (FieldError fieldError : br.getFieldErrors()) {
                log.info("field error = {}", fieldError);
            }

            br.getFieldErrors().forEach(el -> res.setErrors(ms.getMessage(el.getCode(), el.getArguments(), null)));
            return res.setStatus(Responser.CLIENT_ERROR);
        }

        Optional<Member> search = memberService.searchByEmail(dto.getEmail());
        if(search.isEmpty()) {
            throw new ObjectNotFoundException("user not found");
        }

        Member member = search.get();
        if(!encoder.matches(dto.getPwd(), member.getPwd())) {
            throw new AuthFailException("auth fail");
        }

        TokenDto token = jwtManager.createToken(member.getId());
        HashMap<String, String> map = new HashMap<>();
        map.put("accessToken", token.getAccessToken());
        if(dto.getRemember()) {
            Optional<Integer> update = memberService.update(MemberDto.builder().id(member.getId()).token(token.getRefreshToken()).build());
            if(update.isEmpty()) {
                throw new Exception("server db error");
            }

            map.put("refreshToken", token.getRefreshToken());
        }

        return res.setData(map);
    }
}

