package team.foobar.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Banner extends DateEntity{
    @Id @GeneratedValue
    private int id;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "position_sys", nullable = false)
    private Syscode positionSys;

    @Column(nullable = false)
    private String name;

    @Column(name = "start_dt", nullable = false)
    private LocalDateTime startDt;

    @Column(name = "end_dt", nullable = false)
    private LocalDateTime endDt;

    @Column(name = "use_fl", columnDefinition = "boolean default true")
    private boolean useFl;

    @Column(columnDefinition = "integer default 1")
    private int ord;

    private Banner(Syscode positionSys, String name, LocalDateTime startDt, LocalDateTime endDt, int ord) {
        this.positionSys = positionSys;
        this.name = name;
        this.startDt = startDt;
        this.endDt = endDt;
        this.ord = ord;
    }

    public static Banner createBanner(Syscode positionSys, String name, LocalDateTime startDt, LocalDateTime endDt, int ord) {
        return new Banner(positionSys, name, startDt, endDt, ord);
    }
}
