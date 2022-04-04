package team.foobar.config.intercepter;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;
import team.foobar.exception.AuthFailException;
import team.foobar.exception.JwtFailException;
import team.foobar.util.JwtManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtManager jwtManager;

    @Autowired
    private Environment env;


    /* token 없어도 되는 목록 */
    private final String[] whiteList = {"GET:/api/board"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(env.getActiveProfiles().equals("dev")) {
            return true;
        }

        for (String s : whiteList) {
            if (s.startsWith(request.getMethod()) && s.split(":")[1].equals(request.getRequestURI())) {
                return true;
            }
        }

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader == null) {
            throw new AuthFailException("invalid authorization");
        }

        Boolean validateToken = jwtManager.validateToken(authHeader);
        if(!validateToken) {
            throw new JwtFailException("invalid jwt token");
        }

        HttpSession session = request.getSession(true);
        if(session.getAttribute("memberId") == null) {
            Claims claims = jwtManager.parseToken(authHeader);
            session.setAttribute("memberId", claims.get("id", Integer.class));
        }

        return true;
    }
}