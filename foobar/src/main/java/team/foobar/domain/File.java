package team.foobar.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class File extends DateEntity {
    @Id @GeneratedValue
    private int id;

    @Column(name = "target_tb")
    private String targetTable;

    @Column(name = "target_id")
    private String targetId;

    @Column(name = "context", length = 500)
    private String context;

    private File(String targetTable, String targetId, String context) {
        this.targetTable = targetTable;
        this.targetId = targetId;
        this.context = context;
    }

    public static File createFile(String targetTable, String targetId, String context) {
        return new File(targetTable, targetId, context);
    }
}
