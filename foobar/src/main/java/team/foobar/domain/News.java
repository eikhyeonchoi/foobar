package team.foobar.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class News extends DateEntity {
    @Id @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false, length = 500)
    private String source;

    private News(Member member, String message, String source) {
        this.member = member;
        this.message = message;
        this.source = source;
    }

    public static News createNews(Member member, String message, String source) {
        return new News(member, message, source);
    }
}
