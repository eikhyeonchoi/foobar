package team.foobar.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = {"fromMember", "toMember"})
public class Block extends DateEntity {
    @Id @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_member_id", nullable = false)
    private Member fromMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_member_id", nullable = false)
    private Member toMember;

    @Builder
    public Block(Integer id, Member fromMember, Member toMember) {
        this.id = id;
        this.fromMember = fromMember;
        this.toMember = toMember;
    }
}

