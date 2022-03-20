package team.foobar.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = "positionSys")
public class Banner extends DateEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
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

    @Column(columnDefinition = "Integereger default 1", nullable = false)
    private Integer ord;

    @Builder
    public Banner(Integer id, Syscode positionSys, String name, LocalDateTime startDt, LocalDateTime endDt, Boolean useFl, Integer ord) {
        this.id = id;
        this.positionSys = positionSys;
        this.name = name;
        this.startDt = startDt;
        this.endDt = endDt;
        this.useFl = useFl;
        this.ord = ord;
    }

    public void change(Syscode positionSys, String name, LocalDateTime startDt, LocalDateTime endDt, Boolean useFl, Integer ord) {
        if (positionSys != null) {
            this.positionSys = positionSys;
        }
        if (name != null) {
            this.name = name;
        }
        if (startDt != null) {
            this.startDt = startDt;
        }
        if (endDt != null) {
            this.endDt = endDt;
        }
        if (useFl != null) {
            this.useFl = useFl;
        }
        if (ord != null) {
            this.ord = ord;
        }
    }
}
