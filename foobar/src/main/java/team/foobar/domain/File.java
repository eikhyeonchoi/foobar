package team.foobar.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class File extends DateEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "target_tb")
    private String targetTable;

    @Column(name = "target_id")
    private Integer targetId;

    @Column(name = "context", length = 500)
    private String context;

    @Builder
    public File(Integer id, String targetTable, Integer targetId, String context) {
        this.id = id;
        this.targetTable = targetTable;
        this.targetId = targetId;
        this.context = context;
    }
}
