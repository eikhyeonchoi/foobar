package team.foobar.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import team.foobar.dto.auth.TokenDto;
import team.foobar.exception.JwtFailException;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtManager {
    @Value("${jwt.key}")
    private String jwtKey;
    private static final int ACCESS_TOKEN_PERIOD = 1000 * 60 * 60 * 3; // 3 hour
    private static final int REFRESH_TOKEN_PERIOD = 1000 * 60 * 60 * 24 * 7; // 7 days

    private Key getKey() {
        return Keys.hmacShaKeyFor(this.jwtKey.getBytes(StandardCharsets.UTF_8));
    }

    public TokenDto createToken(Integer id) {
        long time = new Date().getTime();
        String access = Jwts.builder()
                .setSubject("accessToken")
                .claim("id", id)
                .claim("tokenType", "access")
                .setExpiration(new Date(time + ACCESS_TOKEN_PERIOD))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

        String refresh = Jwts.builder()
                .setSubject("refreshToken")
                .claim("id", id)
                .claim("tokenType", "refresh")
                .setExpiration(new Date(time + REFRESH_TOKEN_PERIOD))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

        return new TokenDto(access,refresh);
    }


    public Boolean validateToken(String header) {
        String token = this.extractToken(header);

        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
         return false;
    }

    public Claims parseToken(String header) {
        String token = this.extractToken(header);
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
    }


    public String extractToken(String header) {
        if(header == null || !header.startsWith("Bearer ")) {
            throw new JwtFailException("invalid jwt token(extract)");
        }

        return header.substring("Bearer ".length());
    }
}
