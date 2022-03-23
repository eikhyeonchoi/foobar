package team.foobar.categoryboard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Board;
import team.foobar.domain.CategoryBoard;
import team.foobar.dto.board.BoardDto;
import team.foobar.dto.categoryboard.CategoryBoardDto;
import team.foobar.service.board.BoardService;
import team.foobar.service.category.CategoryService;
import team.foobar.service.categoryboard.CategoryBoardService;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Transactional(readOnly = true)
class CategoryBoardServiceImplTest {
    @Autowired
    BoardService boardService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryBoardService service;
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void create() {
        Integer boardPk = boardService.create(BoardDto.builder().title("title").text("text").html("html").memberId(1).build()).get();
        System.out.println("boardPk = " + boardPk);

        em.flush();
        em.clear();

        Page<Board> pageResult = boardService.searchAll(PageRequest.of(0, 100));
        List<Board> list = pageResult.getContent();
        System.out.println("list.get(0) = " + list.get(0));

        CategoryBoardDto dto = CategoryBoardDto.builder().categoryId(1).boardId(list.get(0).getId()).build();

        Integer pk = service.create(dto).get();
        assertThat(pk).isEqualTo(1);

        service.delete(pk);
    }

    @Test
    void searchByCategoryId() {
        Page<CategoryBoard> pageResult = service.searchBoardByCategoryId(1, PageRequest.of(0, 100));
        List<CategoryBoard> list = pageResult.getContent();

        for (CategoryBoard c : list) {
            System.out.println("c.getBoard().getId() = " + c.getBoard().getId());
            System.out.println("c.getBoard().getTitle() = " + c.getBoard().getTitle());
            System.out.println("c.getBoard().getTextContent() = " + c.getBoard().getTextContent());
        }
    }
}