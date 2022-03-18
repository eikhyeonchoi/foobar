package team.foobar.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board extends DateEntity {
    @Id @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(name = "html_content", nullable = false, columnDefinition = "TEXT")
    private String htmlContent;

    @Column(name = "text_content", nullable = false, columnDefinition = "TEXT")
    private String textContent;

    @Column(name = "view_cnt", columnDefinition = "integer default 0")
    private int viewCnt;

    @Column(name = "open_fl", columnDefinition = "boolean default true")
    private boolean openFl;

    @Column(name = "fix_fl", columnDefinition = "boolean default false")
    private boolean fixFl;

    protected Board(Member member, String title, String htmlContent, String textContent) {
        this.member = member;
        this.title = title;
        this.htmlContent = htmlContent;
        this.textContent = textContent;
    }

    public static Board createBoard(Member member, String title, String htmlContent, String textContent) {
        return new Board(member, title, htmlContent, textContent);
    }
}
