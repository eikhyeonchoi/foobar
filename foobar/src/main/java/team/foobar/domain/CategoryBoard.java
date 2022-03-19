package team.foobar.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CategoryBoard extends DateEntity {
    @Id @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    private CategoryBoard(Category boardId, Board categoryId) {
        this.category = category;
        this.board = board;
    }

    public static CategoryBoard make(Category categoryId, Board boardId) {
        return new CategoryBoard(categoryId, boardId);
    }
}
