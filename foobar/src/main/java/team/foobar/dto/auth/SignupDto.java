package team.foobar.dto.auth;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class SignupDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String pwd;
    @NotBlank
    private String nickname;
}
