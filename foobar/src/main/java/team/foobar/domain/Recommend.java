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
    @Id @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "target_tb", nullable = false)
    private String targetTable;

    @Column(name = "target_id", nullable = false)
    private int targetId;

    private Recommend(Member member, String targetTable, int targetId) {
        this.member = member;
        this.targetTable = targetTable;
        this.targetId = targetId;
    }

    public static Recommend createRecommend(Member member, String targetTable, int targetId) {
        return new Recommend(member, targetTable, targetId);
    }
}
