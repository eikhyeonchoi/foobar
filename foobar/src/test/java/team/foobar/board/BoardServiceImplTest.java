package team.foobar.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Board;
import team.foobar.dto.board.BoardDto;
import team.foobar.service.board.BoardService;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional(readOnly = true)
class BoardServiceImplTest {

    @Autowired
    BoardService service;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void update() {
        BoardDto dto = BoardDto.builder().memberId(1).html("<p>haha</p>").text("haha").title("titletitle").build();
        Optional<Integer> pk = service.create(dto);
        em.flush();
        em.clear();

        if(pk.isPresent()) {
            Board board = service.search(pk.get()).get();
            assertThat(board.getTextContent()).isEqualTo("haha");

            board.change(null, "titleUpdate", null, null, 2, null, null);
            em.flush();
            em.clear();


            Board findBoard = service.search(board.getId()).get();
            assertThat(findBoard.getTitle()).isEqualTo("titleUpdate");

            Page<Board> pageResult = service.searchByMemberId(1, PageRequest.of(0, 100));
            List<Board> boards = pageResult.getContent();
        }
    }
}