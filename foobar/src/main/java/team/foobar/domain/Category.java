package team.foobar.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = "typeSys")
@DynamicInsert
public class Category extends DateEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_sys", nullable = false)
    private Syscode typeSys;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "integer default 1", nullable = false)
    private Integer ord;

    @Column(name = "use_fl", columnDefinition = "boolean default true", nullable = false)
    private Boolean useFl;

    @Builder
    public Category(Integer id, Syscode typeSys, String name, Integer ord, Boolean useFl) {
        this.id = id;
        this.typeSys = typeSys;
        this.name = name;
        this.ord = ord;
        this.useFl = useFl;
    }

    public void change(Syscode typeSys, String name, Integer ord, Boolean useFL) {
        if (typeSys != null) {
            this.typeSys = typeSys;
        }
        if (name != null) {
            this.name = name;
        }
        if (ord != null && ord > 0) {
            this.ord = ord;
        }
        if (useFL != null) {
            this.useFl = useFL;
        }
    }
}
