package team.foobar.controller;


import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.foobar.util.JwtManager;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/board")
public class BoardController {
    private final JwtManager jwtManager;

    @GetMapping
    public Integer test(HttpServletRequest request) {
        Claims tokenBody = jwtManager.parseToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        Integer id = tokenBody.get("id", Integer.class);
        log.info("id = {}", id);
        return id;
    }
}