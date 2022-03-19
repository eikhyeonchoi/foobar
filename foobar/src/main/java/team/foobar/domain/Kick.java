package team.foobar.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Kick extends DateEntity {
    @Id @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "start_dt", nullable = false)
    private LocalDateTime startDt;

    @Column(name = "end_dt", nullable = false)
    private LocalDateTime endDt;

    private Kick(Member member, LocalDateTime startDt, LocalDateTime endDt) {
        this.member = member;
        this.startDt = startDt;
        this.endDt = endDt;
    }

    public static Kick make(Member member, LocalDateTime startDt, LocalDateTime endDt) {
        return new Kick(member, startDt, endDt);
    }

    public void change(Member member, LocalDateTime startDt, LocalDateTime endDt) {
        if (member != null) {
            this.member = member;
        }

        if (startDt != null) {
            this.startDt = startDt;
        }

        if (endDt != null) {
            this.endDt = endDt;
        }
    }
}
