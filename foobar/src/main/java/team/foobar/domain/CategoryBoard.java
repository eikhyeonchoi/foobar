package team.foobar.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = {"category", "board"})
public class CategoryBoard {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Builder
    public CategoryBoard(Integer id, Category category, Board board) {
        this.id = id;
        this.category = category;
        this.board = board;
    }

    public void change(Category category, Board board) {
        if (category != null) {
            this.category = category;
        }
        if (board != null) {
            this.board = board;
        }
    }
}
