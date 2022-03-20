package team.foobar.dto.syscode;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SyscodeDto {
    private String code;
    private String parentCode;
    private String value;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime createDt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime updateDt;

    @Builder
    public SyscodeDto(String code, String parentCode, String value, LocalDateTime createDt, LocalDateTime updateDt) {
        this.code = code;
        this.parentCode = parentCode;
        this.value = value;
        this.createDt = createDt;
        this.updateDt = updateDt;
    }
}
