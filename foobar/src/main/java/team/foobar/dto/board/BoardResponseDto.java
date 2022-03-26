package team.foobar.dto.board;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import team.foobar.domain.Board;
import team.foobar.dto.member.MemberResponseDto;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class BoardResponseDto {
    private Integer id;
    private MemberResponseDto memberResponseDto;
    private String title;
    private String html;
    private String text;
    private Integer view;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime createDt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime updateDt;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.memberResponseDto = new MemberResponseDto(board.getMember());
        this.title = board.getTitle();
        this.html = board.getHtmlContent();
        this.text = board.getTextContent();
        this.view = board.getViewCnt();
        this.createDt = board.getCreateDt();
        this.updateDt = board.getUpdateDt();
    }
}

