package team.foobar.dto.board;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import team.foobar.domain.CategoryBoard;

import javax.swing.text.StyledEditorKit;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class BoardDto {
    private Integer id;
    private Integer memberId;
    @NotBlank
    private String title;
    private String html;
    @NotBlank
    private String text;
    private Integer view;
    @NotNull
    private Boolean openFl;
    private Boolean fixFl;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime createDt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime updateDt;

    @Builder
    public BoardDto(
            Integer id,
            Integer memberId,
            String title,
            String html,
            String text,
            Integer view,
            Boolean openFl,
            Boolean fixFl,
            LocalDateTime createDt,
            LocalDateTime updateDt) {
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
