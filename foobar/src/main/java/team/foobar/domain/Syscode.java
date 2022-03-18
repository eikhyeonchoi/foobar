package team.foobar.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Syscode extends DateEntity {
    @Id
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_sys", columnDefinition = "VARCHAR(100)")
    private Syscode parentSys;

    @Column(nullable = false)
    private String value;

    private Syscode(String code, Syscode parentSys, String value) {
        this.code = code;
        this.parentSys = parentSys;
        this.value = value;
    }

    private Syscode(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Syscode createSyscode(String code, Syscode parentSys, String value) {
        return new Syscode(code, parentSys, value);
    }

    public static Syscode createRootCode() {
        return new Syscode("root", "루트");
    }
}
