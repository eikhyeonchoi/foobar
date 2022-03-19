package team.foobar.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Scrap extends DateEntity {
    @Id @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private Scrap(Member member, Board board) {
        this.member = member;
        this.board = board;
    }

    public static Scrap make(Member member, Board board) {
        return new Scrap(member, board);
    }

    public void change(Member member, Board board) {
        if (member != null) {
            this.member = member;
        }

        if (board != null) {
            this.board = board;
        }
    }
}
