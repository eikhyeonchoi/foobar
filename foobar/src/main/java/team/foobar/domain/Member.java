package team.foobar.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = "roleSys")
public class Member extends DateEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_sys", nullable = false)
    private Syscode roleSys;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Builder
    public Member(Integer id, Syscode roleSys, String email, String pwd, String nickname, String refreshToken) {
        this.id = id;
        this.roleSys = roleSys;
        this.email = email;
        this.pwd = pwd;
        this.nickname = nickname;
        this.refreshToken = refreshToken;
    }

    public void change(Syscode roleSys, String email, String pwd, String nickname, String token) {
        if (roleSys != null) {
            this.roleSys = roleSys;
        }
        if (email != null) {
            this.email = email;
        }
        if (pwd != null) {
            this.pwd = pwd;
        }
        if (nickname != null) {
            this.nickname = nickname;
        }
        if (token != null) {
            this.refreshToken = token;
        }
    }
}
