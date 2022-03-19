package team.foobar.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = "positionSys")
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

    @Column(name = "use_fl", columnDefinition = "boolean default true", nullable = false)
    private Boolean useFl;

    @Column(columnDefinition = "integer default 1", nullable = false)
    private Integer ord;

    @Builder
    public Banner(int id, Syscode positionSys, String name, LocalDateTime startDt, LocalDateTime endDt, Boolean useFl, Integer ord) {
        this.id = id;
        this.positionSys = positionSys;
        this.name = name;
        this.startDt = startDt;
        this.endDt = endDt;
        this.useFl = useFl;
        this.ord = ord;
    }
}
