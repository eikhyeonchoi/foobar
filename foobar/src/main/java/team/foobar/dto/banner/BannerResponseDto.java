package team.foobar.dto.banner;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import team.foobar.domain.Banner;
import team.foobar.domain.Syscode;
import team.foobar.dto.syscode.SyscodeDto;
import java.time.LocalDateTime;

@Data


public class BannerResponseDto {
    private Integer id;
    private SyscodeDto positionSys;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime startDt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime endDt;
    private Integer ord;

    public BannerResponseDto(Banner banner) {
        this.id = banner.getId();
        Syscode s = banner.getPositionSys();
        this.positionSys = SyscodeDto.builder().code(s.getCode()).value(s.getValue()).build();
        this.name = banner.getName();
        this.startDt = banner.getStartDt();
        this.endDt = banner.getEndDt();
        this.ord = banner.getOrd();
    }


}
