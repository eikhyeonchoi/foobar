package team.foobar.config.intercepter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;
import team.foobar.exception.JwtFailException;
import team.foobar.util.JwtManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
     @Autowired
     JwtManager jwtManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        Boolean validateToken = jwtManager.validateToken(extractToken(authHeader));
        log.info("validateToken = {}", validateToken);
        return validateToken;
    }

    private String extractToken(String header) {
        if(header == null || !header.startsWith("Bearer ")) {
            throw new JwtFailException("jwt parse fail");
        }

        return header.substring("Bearer ".length());
    }
}
