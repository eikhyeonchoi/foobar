package team.foobar.repository.jpa.categoryboard;

import org.springframework.data.jpa.repository.JpaRepository;
import team.foobar.domain.CategoryBoard;

public interface CategoryBoardRepository extends JpaRepository<CategoryBoard, Integer>, CategoryBoardRepositoryCustom {
}
