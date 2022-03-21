package team.foobar.repository.jpa.category;

import team.foobar.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryCustom {
    Optional<Category> findByIdWithFetch(Integer id);
    List<Category> findAllWithFetch();
    Integer findLastOrd();
}
