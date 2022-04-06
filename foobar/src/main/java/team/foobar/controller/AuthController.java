package team.foobar.controller;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.foobar.domain.Member;
import team.foobar.dto.Responser;
import team.foobar.dto.auth.LoginDto;
import team.foobar.dto.auth.SignupDto;
import team.foobar.dto.auth.TokenDto;
import team.foobar.dto.member.MemberDto;
import team.foobar.exception.AuthFailException;
import team.foobar.exception.DuplicateObjectException;
import team.foobar.exception.JwtFailException;
import team.foobar.exception.ObjectNotFoundException;
import team.foobar.service.member.MemberService;
import team.foobar.util.JwtManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    private final static String LOGIN_SESSION_KEY = "memberId";

    @PostMapping("/login")
    public Responser<Object> login(@Validated @RequestBody LoginDto dto, BindingResult br, HttpServletRequest request) throws Exception {
        Responser<Object> res = new Responser<>();
        if(br.hasFieldErrors()) {
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

        HashMap<String, String> map = new HashMap<>();
        TokenDto token = jwtManager.createToken(member.getId());
        map.put("accessToken", token.getAccessToken());
        if(dto.getRemember()) {
            Optional<Integer> update = memberService.update(MemberDto.builder().id(member.getId()).token(token.getRefreshToken()).build());
            if(update.isEmpty()) {
                throw new Exception("server db error");
            }

            map.put("refreshToken", token.getRefreshToken());
        }

        HttpSession session = request.getSession(true);
        if(session.getAttribute("memberId") == null) {
            session.setAttribute("memberId", member.getId());
        }

        return res.setData(map);
    }

    @GetMapping("/logout")
    public Responser<Object> logout(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        Object id = session.getAttribute(LOGIN_SESSION_KEY);

        if(id != null) {
            Optional<Member> search = memberService.search((Integer) id);
            if(search.isPresent()) {
                Member member = search.get();
                Optional<Integer> update = memberService.update(MemberDto.builder().id(member.getId()).token("").build());
                if(update.isEmpty()) {
                    throw new Exception("server db error");
                }
            }
        }

        session.removeAttribute(LOGIN_SESSION_KEY);
        return new Responser<>();
    }

    @PostMapping("/signup")
    public Responser<Object> signup(@Validated @RequestBody SignupDto signupDto, BindingResult br) throws Exception {
        Responser<Object> res = new Responser<>();
        if(br.hasFieldErrors()) {
            br.getFieldErrors().forEach(el -> res.setErrors(ms.getMessage(el.getCode(), el.getArguments(), null)));
            return res.setStatus(Responser.CLIENT_ERROR);
        }

        if(memberService.searchByEmail(signupDto.getEmail()).isPresent()) {
            throw new DuplicateObjectException("duplicated email");
        }

        if (memberService.searchByNickname(signupDto.getNickname()).isPresent()) {
            throw new DuplicateObjectException("duplicated nickname");
        }

        Optional<Integer> createMember = memberService.create(
                MemberDto.builder()
                        .syscode("user_role_common")
                        .email(signupDto.getEmail())
                        .pwd(encoder.encode(signupDto.getPwd()))
                        .nickname(signupDto.getNickname())
                        .build()
        );

        if(createMember.isEmpty()) {
            throw new Exception("server db error");
        }

        return res;
    }

    /**
     * access token 만료시 refresh token으로 2token 재발급
     * @Param HttpServletRequest
     * @return Responser<Map>
     * @throws Exception
     */
    @GetMapping("/refresh")
    public Responser<Object> refresh(HttpServletRequest request) throws Exception {
        Responser<Object> res = new Responser<>();

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header == null) {
            throw new AuthFailException("no authorization header");
        }

        Claims claims = jwtManager.parseToken(header);
        String tokenType = claims.get("tokenType", String.class);
        if(!tokenType.equals("refresh")) {
            throw new JwtFailException("invalid refresh token(no refresh token)");
        }

        Optional<Member> search = memberService.search(claims.get("id", Integer.class));
        if(search.isEmpty()) {
            throw new JwtFailException("invalid refresh token(empty object)");
        }

        String token = jwtManager.extractToken(header);
        Member member = search.get();
        if(!token.equals(member.getRefreshToken())) {
            throw new JwtFailException("invalid refresh token(not matched member)");
        }

        HashMap<String, String> map = new HashMap<>();
        TokenDto tokenDto = jwtManager.createToken(member.getId());
        map.put("accessToken", tokenDto.getAccessToken());
        map.put("refreshToken", tokenDto.getRefreshToken());
        Optional<Integer> update = memberService.update(MemberDto.builder().id(member.getId()).token(tokenDto.getRefreshToken()).build());
        if(update.isEmpty()) {
            throw new Exception("server db error");
        }

        return res.setData(map);
    }

}

