package team.foobar.dto.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentDto {
    private Integer id;
    private Integer boardId;
    private Integer memberId;
    private Integer tagMemberId;
    private String content;
    private Integer parent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime createDt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime updateDt;

    @Builder
    public CommentDto(Integer id, Integer boardId, Integer memberId, Integer tagMemberId, String content, Integer parent, LocalDateTime createDt, LocalDateTime updateDt) {
        this.id = id;
        this.boardId = boardId;
        this.memberId = memberId;
        this.tagMemberId = tagMemberId;
        this.content = content;
        this.parent = parent;
        this.createDt = createDt;
        this.updateDt = updateDt;
    }
}
