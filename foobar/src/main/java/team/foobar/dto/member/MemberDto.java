package team.foobar.dto.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MemberDto {
    private Integer id;
    private String syscode;
    private String syscodeValue;
    private String email;
    private String pwd;
    private String nickname;
    private String token;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime createDt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime updateDt;

    @Builder
    public MemberDto(Integer id, String syscode, String syscodeValue, String email, String pwd, String nickname, String token, LocalDateTime createDt, LocalDateTime updateDt) {
        this.id = id;
        this.syscode = syscode;
        this.syscodeValue = syscodeValue;
        this.email = email;
        this.pwd = pwd;
        this.nickname = nickname;
        this.token = token;
        this.createDt = createDt;
        this.updateDt = updateDt;
    }

    public void hashPassword(String pwd) {
        this.pwd = pwd;
    }
}
