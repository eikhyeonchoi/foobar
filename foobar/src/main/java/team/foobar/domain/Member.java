package team.foobar.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends DateEntity {
    @Id @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_sys", nullable = false)
    private Syscode roleSys;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String pwd;

    @Column(name = "refresh_token")
    private String refreshToken;


    private Member(Syscode roleSys, String email, String pwd) {
        this.roleSys = roleSys;
        this.email = email;
        this.pwd = pwd;
    }

    public static Member make(Syscode roleSys, String email, String pwd) {
        return new Member(roleSys, email, pwd);
    }

    public void change(Syscode roleSys, String email, String pwd) {
        if (roleSys != null) {
            this.roleSys = roleSys;
        }

        if (email != null) {
            this.email = email;
        }

        if (pwd != null) {
            this.pwd = pwd;
        }
    }


}
