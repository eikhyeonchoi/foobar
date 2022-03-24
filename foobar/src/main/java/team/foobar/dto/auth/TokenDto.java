package team.foobar.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class TokenDto {
    private String accessToken;
    private String refreshToken;
}
