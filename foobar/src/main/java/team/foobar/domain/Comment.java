package team.foobar.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends DateEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_member_id")
    private Member tagMember;

    @Lob
    @Column(nullable = false)
    private String content;

    private Comment(Member member, Member tagMember, String content) {
        this.member = member;
        this.tagMember = tagMember;
        this.content = content;
    }

    public static Comment make(Member member, Member tagMember, String content) {
        return new Comment(member, tagMember, content);
    }

    public void change(Member member, Member tagMember, String content) {
        if (member != null) {
            this.member = member;
        }

        if (tagMember != null) {
            this.tagMember = tagMember;
        }

        if (content != null) {
            this.content = content;
        }
    }
}
