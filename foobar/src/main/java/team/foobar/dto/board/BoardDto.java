package team.foobar.dto.board;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.text.StyledEditorKit;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDto {
    private Integer id;
    private Integer memberId;
    private String title;
    private String html;
    private String text;
    private Integer view;
    private Boolean openFl;
    private Boolean fixFl;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime createDt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime updateDt;

    @Builder
    public BoardDto(Integer id, Integer memberId, String title, String html, String text, Integer view, Boolean openFl, Boolean fixFl, LocalDateTime createDt, LocalDateTime updateDt) {
        this.id = id;
        this.memberId = memberId;
        this.title = title;
        this.html = html;
        this.text = text;
        this.view = view;
        this.openFl = openFl;
        this.fixFl = fixFl;
        this.createDt = createDt;
        this.updateDt = updateDt;
    }
}
