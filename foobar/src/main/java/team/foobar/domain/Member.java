package team.foobar.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends DateEntity {
    @Id @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String pwd;

    @Column(name = "refresh_token")
    private String refreshToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_sys", nullable = false)
    private Syscode roleSys;

    private Member(String email, String pwd, Syscode roleSys) {
        this.email = email;
        this.pwd = pwd;
        this.roleSys = roleSys;
    }

    public static Member createMember(String email, String pwd, Syscode roleSys) {
        return new Member(email, pwd, roleSys);
    }
}
