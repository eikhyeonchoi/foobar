package team.foobar.service.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ManyToAny;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Category;
import team.foobar.repository.jpa.category.CategoryRepository;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Primary
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public Category search(Integer categoryId) {
        return repository.findById(categoryId).orElse(null);
    }

    @Override
    public List<Category> searchAll() {
        return repository.findAll();
    }

    @Override
    public Category create(Category category) {
        return repository.save(category);
    }

    @Override
    public void delete(Integer categoryId) {
        repository.deleteById(categoryId);
    }
}
