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
            Syscode cpp = Syscode.builder().code("category_cpp").parentSys(categorySys).value("C++").build();
            em.persist(cpp);
            Syscode py = Syscode.builder().code("category_py").parentSys(categorySys).value("python").build();
            em.persist(py);

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
            Category javaCategory = Category.builder().typeSys(java).name("java").build();
            em.persist(javaCategory);

            Category cppCategory = Category.builder().typeSys(java).name("cpp").build();
            em.persist(cppCategory);

            Category pyCategory = Category.builder().typeSys(java).name("py").build();
            em.persist(pyCategory);


            /* board */
            Member member = em.find(Member.class, 1);
            for (int i = 0; i < 3; i++) {
                Board board = Board.builder().member(member).title("title" + i).htmlContent("html" + i).textContent("text" + i).build();
                em.persist(board);
                em.persist(CategoryBoard.builder().category(javaCategory).board(board).build());
                em.persist(CategoryBoard.builder().category(cppCategory).board(board).build());
                em.persist(CategoryBoard.builder().category(pyCategory).board(board).build());
            }


            /* comment */
            Member findMember = em.find(Member.class, 1);
            Board findBoard = em.find(Board.class, 1);
            Comment rootComment = Comment.builder().board(findBoard).member(findMember).content("content").build();
            em.persist(rootComment);


            Member member2 = em.find(Member.class, 2);
            for (int i = 0; i < 5; i++) {
                em.persist(Comment.builder().board(findBoard).member(member2).content("reComment" + i).parent(rootComment).build());
            }
        }

        public void clear() {
            em.flush();
            em.clear();
        }
    }
}
