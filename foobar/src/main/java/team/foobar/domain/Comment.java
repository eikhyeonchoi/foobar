package team.foobar.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = {"board", "member", "tagMember", "parent", "childList"})
@DynamicInsert
public class Comment extends DateEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_member_id")
    private Member tagMember;

    @Lob
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> childList = new ArrayList<>();

    @Builder
    public Comment(Integer id, Board board, Member member, Member tagMember, String content, Comment parent) {
        this.id = id;
        this.board = board;
        this.member = member;
        this.tagMember = tagMember;
        this.content = content;
        this.parent = parent;
    }

    public void change(Member tagMember, String content) {
        if (tagMember != null) {
            this.tagMember = tagMember;
        }
        if (content != null) {
            this.content = content;
        }
    }

    public void replaceChild(List<Comment> childList) {
        this.childList = childList;
    }
}