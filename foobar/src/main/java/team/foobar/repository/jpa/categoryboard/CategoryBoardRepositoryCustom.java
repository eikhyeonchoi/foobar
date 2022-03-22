package team.foobar.repository.jpa.categoryboard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.CategoryBoard;

public interface CategoryBoardRepositoryCustom {
    Page<CategoryBoard> findBoardByCategoryId(Integer id, Pageable pageable);
}
