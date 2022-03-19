package team.foobar.service.category;

import team.foobar.domain.Category;

import java.util.List;

public interface CategoryService {
    Category search(Integer categoryId);
    List<Category> searchAll();
    Category create(Category category);
    void delete(Integer categoryId);
}
