package team.foobar.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@DynamicInsert
public class Board extends DateEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(name = "html_content", nullable = false)
    private String htmlContent;

    @Lob
    @Column(name = "text_content", nullable = false)
    private String textContent;

    @Column(name = "view_cnt", columnDefinition = "integer default 0", nullable = false)
    private Integer viewCnt;

    @Column(name = "open_fl", columnDefinition = "boolean default true", nullable = false)
    private Boolean openFl;

    @Column(name = "fix_fl", columnDefinition = "boolean default false", nullable = false)
    private Boolean fixFl;

    @Builder
    public Board(Integer id, Member member, String title, String htmlContent, String textContent, Integer viewCnt, Boolean openFl, Boolean fixFl) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.htmlContent = htmlContent;
        this.textContent = textContent;
        this.viewCnt = viewCnt;
        this.openFl = openFl;
        this.fixFl = fixFl;
    }


    public void change(Member member, String title, String htmlContent, String textContent, Integer viewCnt, Boolean openFl, Boolean fixFl) {
        if (member != null) {
            this.member = member;
        }
        if (title != null) {
            this.title = title;
        }
        if (htmlContent != null) {
            this.htmlContent = htmlContent;
        }
        if (textContent != null) {
            this.textContent = textContent;
        }
        if (viewCnt != null) {
            this.viewCnt = viewCnt;
        }
        if (openFl != null) {
            this.openFl = openFl;
        }
        if (fixFl != null) {
            this.fixFl = fixFl;
        }
    }
}
