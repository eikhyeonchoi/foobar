package team.foobar.service.categoryboard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Board;
import team.foobar.domain.Category;
import team.foobar.domain.CategoryBoard;
import team.foobar.dto.categoryboard.CategoryBoardDto;

import java.util.Optional;

public interface CategoryBoardService {
    Page<CategoryBoard> searchBoardByCategoryId(Integer id, Pageable pageable);
    Optional<Integer> create(CategoryBoardDto dto);
    void delete(Integer id);

    default CategoryBoard dtoToEntity(CategoryBoardDto dto) {
        return CategoryBoard.builder()
                .id(dto.getId())
                .category(Category.builder().id(dto.getCategoryId()).build())
                .board(Board.builder().id(dto.getBoardId()).build())
                .build();
    }

    default CategoryBoardDto entityToDto(CategoryBoard cb) {
        return CategoryBoardDto.builder()
                .id(cb.getId())
                .categoryId(cb.getCategory().getId())
                .boardId(cb.getBoard().getId())
                .build();
    }
}
