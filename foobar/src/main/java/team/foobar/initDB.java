package team.foobar;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.foobar.domain.Member;
import team.foobar.domain.Syscode;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class initDB {

    private final initService service;

    @PostConstruct
    public void startInit() {
        service.initDb1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class initService {
        private final EntityManager em;
        public void initDb1() {

            /* syscode */
            Syscode rootSys = Syscode.createRootCode();
            em.persist(rootSys);

            Syscode userRoleSys = Syscode.createSyscode("user_role", rootSys, "유저역할");
            em.persist(userRoleSys);

            Syscode commonUserSys = Syscode.createSyscode("user_role_common", userRoleSys, "일반유저");
            em.persist(commonUserSys);
            em.persist(Syscode.createSyscode("user_role_admin", userRoleSys, "관리자유저"));


            Syscode bannerPositionSys = Syscode.createSyscode("banner_position", rootSys, "배너위치");
            em.persist(bannerPositionSys);

            em.persist(Syscode.createSyscode("banner_position_main_header", bannerPositionSys, "메인헤더"));
            em.persist(Syscode.createSyscode("banner_position_main_side", bannerPositionSys, "메인사이드바"));
            em.persist(Syscode.createSyscode("banner_position_main_footer", bannerPositionSys, "메인푸터"));


            /* member */
            for (int i = 0; i < 10; i++) {
                em.persist(Member.createMember("temp"+ i +"@gmail.com", "123", commonUserSys));
            }
        }
    }
}
