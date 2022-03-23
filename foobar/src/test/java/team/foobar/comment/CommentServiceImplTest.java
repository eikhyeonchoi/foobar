package team.foobar.comment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Comment;
import team.foobar.dto.comment.CommentDto;
import team.foobar.repository.jpa.comment.CommentRepository;
import team.foobar.service.board.BoardService;
import team.foobar.service.comment.CommentService;
import team.foobar.service.member.MemberService;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional(readOnly = true)
class CommentServiceImplTest {
    @Autowired
    CommentService service;
    @Autowired
    CommentRepository repository;
    @Autowired
    BoardService boardService;
    @Autowired
    MemberService memberService;
    @Autowired
    EntityManager em;

    @Test
    void searchByBoardId() {
        List<Comment> list = service.searchByBoardId(1);

        for (Comment comment : list) {
            System.out.println("comment = " + comment);

            List<Comment> childList = comment.getChildList();
            for (Comment child : childList) {
                System.out.println("child = " + child);
            }
        }
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void update() {
        CommentDto dto = CommentDto.builder().memberId(1).boardId(1).content("this is comment").build();
        Optional<Integer> cc = service.create(dto);

        if(cc.isEmpty()) {
            CommentDto dto2 = CommentDto.builder().id(cc.get()).content("this is update comment").build();
            Optional<Integer> update = service.update(dto2);

            Comment comment = service.search(dto2.getId()).get();
            assertThat(comment.getContent()).isEqualTo("this is update comment");
        }
    }

    @Test
    @Transactional
    @Rollback(false)
    void delete() {
        service.delete(1);
        Optional<Comment> search = service.search(1);
        assertThat(search.get().getDeleteFL()).isEqualTo(true);
    }
}