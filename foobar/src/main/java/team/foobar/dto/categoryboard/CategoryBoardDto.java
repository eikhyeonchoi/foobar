package team.foobar.dto.categoryboard;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class CategoryBoardDto {
    private Integer id;
    private Integer categoryId;
    private Integer boardId;
    private String categoryName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime createDt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    private LocalDateTime updateDt;

    @Builder
    public CategoryBoardDto(Integer id, Integer categoryId, Integer boardId, String categoryName) {
        this.id = id;
        this.categoryId = categoryId;
        this.boardId = boardId;
        this.categoryName = categoryName;
    }
}
