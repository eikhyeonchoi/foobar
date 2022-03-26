package team.foobar.dto.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import team.foobar.domain.Member;
import team.foobar.dto.syscode.SyscodeDto;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MemberResponseDto {
    private Integer id;
    private SyscodeDto syscodeDto;
    private String email;
    private String nickname;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime createDt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime updateDt;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.syscodeDto = SyscodeDto.builder().code(member.getRoleSys().getCode()).value(member.getRoleSys().getValue()).build();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.createDt = member.getCreateDt();
        this.updateDt = member.getUpdateDt();
    }
}
