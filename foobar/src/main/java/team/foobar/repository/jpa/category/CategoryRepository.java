package team.foobar.repository.jpa.category;

import org.springframework.data.jpa.repository.JpaRepository;
import team.foobar.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>, CategoryRepositoryCustom {
}
