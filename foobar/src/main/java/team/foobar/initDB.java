package team.foobar;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.foobar.domain.*;

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
            Syscode rootSys = Syscode.builder().code("root").value("루트").build();
            em.persist(rootSys);

            Syscode userRoleSys = Syscode.builder().code("user_role").parentSys(rootSys).value("유저역할").build();
            em.persist(userRoleSys);

            Syscode commonMemberSys = Syscode.builder().code("user_role_common").parentSys(userRoleSys).value("일반").build();
            em.persist(commonMemberSys);
            Syscode admin = Syscode.builder().code("user_role_admin").parentSys(userRoleSys).value("관리자").build();
            em.persist(admin);


            Syscode bannerPositionSys = Syscode.builder().code("banner_position").parentSys(rootSys).value("배너위치").build();
            em.persist(bannerPositionSys);

            em.persist(Syscode.builder().code("banner_position_main_header").parentSys(bannerPositionSys).value("메인헤더").build());
            em.persist(Syscode.builder().code("banner_position_main_side").parentSys(bannerPositionSys).value("메인사이드바").build());
            em.persist(Syscode.builder().code("banner_position_main_footer").parentSys(bannerPositionSys).value("메인푸터").build());


            Syscode categorySys = Syscode.builder().code("category").parentSys(rootSys).value("카테고리").build();
            em.persist(categorySys);

            Syscode java = Syscode.builder().code("category_java").parentSys(categorySys).value("자바").build();
            em.persist(java);
            em.persist(Syscode.builder().code("category_cpp").parentSys(categorySys).value("C++").build());
            em.persist(Syscode.builder().code("category_py").parentSys(categorySys).value("python").build());
            em.persist(Syscode.builder().code("category_notice").parentSys(categorySys).value("공지사항").build());


            /* member */
            for (int i = 0; i < 10; i++) {
                em.persist(
                        Member.builder()
                                .roleSys(commonMemberSys)
                                .email("test" + i + "@gmail.com")
                                .pwd("a")
                                .nickname("nickname" + i)
                                .refreshToken("asdnadn345nfn")
                                .build()
                );
            }

            for (int i = 0; i < 2; i++) {
                em.persist(
                        Member.builder()
                                .roleSys(admin)
                                .email("admin" + i + "@gmail.com")
                                .pwd("admin")
                                .nickname("admin" + i)
                                .refreshToken("aaaaaaaaaaaaaaaa")
                                .build()
                );
            }


            /* category */
            Category javaCategory = Category.builder().typeSys(java).name("자바자바").build();
            em.persist(javaCategory);


            /* board */
            Member member = em.find(Member.class, 1);
            for (int i = 0; i < 10; i++) {
                Board board = Board.builder().member(member).title("title" + i).htmlContent("html" + i).textContent("text" + i).build();
                em.persist(board);

                em.persist(CategoryBoard.builder().category(javaCategory).board(board).build());
            }
        }

        public void clear() {
            em.flush();
            em.clear();
        }
    }
}
