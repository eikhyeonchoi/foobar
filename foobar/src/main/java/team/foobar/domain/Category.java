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

    @Column(name = "use_fl", columnDefinition = "boolean default true")
    private boolean useFl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_sys", nullable = false)
    private Syscode typeSys;

    private Category(Syscode typeSys) {
        this.typeSys = typeSys;
    }

    public static Category createCategory(Syscode typeSys) {
        return new Category(typeSys);
    }
}
