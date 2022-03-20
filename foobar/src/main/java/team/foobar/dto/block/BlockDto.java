package team.foobar.dto.block;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockDto {
    private Integer id;
    private Integer fromId;
    private String fromNickname ;
    private Integer toId;
    private String toNickname;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDt;

    @Builder
    public BlockDto(Integer id, Integer fromId, String fromNickname, Integer toId, String toNickname, LocalDateTime createDt, LocalDateTime updateDt) {
        this.id = id;
        this.fromId = fromId;
        this.fromNickname = fromNickname;
        this.toId = toId;
        this.toNickname = toNickname;
        this.createDt = createDt;
        this.updateDt = updateDt;
    }

}
