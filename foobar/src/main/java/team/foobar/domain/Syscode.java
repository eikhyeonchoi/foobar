package team.foobar.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = "parentSys")
public class Syscode extends DateEntity {
    @Id
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_sys", columnDefinition = "VARCHAR(100)")
    private Syscode parentSys;

    @Column(nullable = false)
    private String value;

    @Builder
    public Syscode(String code, Syscode parentSys, String value) {
        this.code = code;
        this.parentSys = parentSys;
        this.value = value;
    }

    public void change(String code, Syscode parentSys, String value) {
        if (code != null) {
            this.code = code;
        }

        if (parentSys != null) {
            this.parentSys = parentSys;
        }

        if (value != null) {
            this.value = value;
        }
    }
}
