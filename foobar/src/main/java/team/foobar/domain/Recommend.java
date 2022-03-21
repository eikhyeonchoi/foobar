package team.foobar.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.type.descriptor.spi.JdbcRecommendedSqlTypeMappingContext;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Recommend extends DateEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "target_tb", nullable = false)
    private String targetTable;

    @Column(name = "target_id", nullable = false)
    private Integer targetId;

    private Recommend(Member member, String targetTable, Integer targetId) {
        this.member = member;
        this.targetTable = targetTable;
        this.targetId = targetId;
    }

    public static Recommend make(Member member, String targetTable, Integer targetId) {
        return new Recommend(member, targetTable, targetId);
    }

    public void change(Member member, String targetTable, Integer targetId) {
        if (member != null) {
            this.member = member;
        }

        if (targetTable != null) {
            this.targetTable = targetTable;
        }

        if (targetId != null && targetId != 0) {
            this.targetId = targetId;
        }
    }
}
