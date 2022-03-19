package team.foobar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BannerDto {
    private Integer id;
    private String syscode;
    private String name;
    private LocalDateTime startDt;
    private LocalDateTime endDt;
    private boolean useFl;
    private Integer ord;
}
