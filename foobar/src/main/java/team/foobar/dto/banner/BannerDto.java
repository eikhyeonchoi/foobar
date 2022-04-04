package team.foobar.dto.banner;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.apache.tomcat.jni.Local;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class BannerDto {
    private Integer id;
    @NotBlank
    private String syscode;
    private String syscodeValue;
    @NotBlank
    private String name;

    @NotNull
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime startDt;

    @NotNull
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime endDt;

    private Boolean useFl = Boolean.TRUE;
    private Integer ord;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime createDt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime updateDt;

    @Builder
    public BannerDto(Integer id, String syscode, String syscodeValue, String name, LocalDateTime startDt, LocalDateTime endDt, boolean useFl, Integer ord, LocalDateTime createDt, LocalDateTime updateDt) {
        this.id = id;
        this.syscode = syscode;
        this.syscodeValue = syscodeValue;
        this.name = name;
        this.startDt = startDt;
        this.endDt = endDt;
        this.useFl = useFl;
        this.ord = ord;
        this.createDt = createDt;
        this.updateDt = updateDt;
    }
}
