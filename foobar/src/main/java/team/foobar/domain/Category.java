package team.foobar.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Category extends DateEntity {
    @Id @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_sys", nullable = false)
    private Syscode typeSys;

    private String name;

    @Column(columnDefinition = "integer default 1")
    private Integer ord;

    @Column(name = "use_fl", columnDefinition = "boolean default true")
    private boolean useFl;

    private Category(Syscode typeSys, String name) {
        this.typeSys = typeSys;
        this.name = name;
    }

    public static Category make(Syscode typeSys, String name) {
        return new Category(typeSys, name);
    }

    public void change(Syscode typeSys, String name, Integer ord) {
        if (typeSys != null) {
            this.typeSys = typeSys;
        }

        if (name != null) {
            this.name = name;
        }

        if (ord != null && ord > 0) {
            this.ord = ord;
        }
    }
}
