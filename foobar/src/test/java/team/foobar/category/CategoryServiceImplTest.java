package team.foobar.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Category;
import team.foobar.domain.Syscode;
import team.foobar.dto.category.CategoryDto;
import team.foobar.service.category.CategoryService;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional(readOnly = true)
class CategoryServiceImplTest {
    @Autowired
    CategoryService service;

    @Autowired
    EntityManager em;

    @Test
    void search() {
        Optional<Category> search = service.search(1);
        Category category = search.get();
        assertThat(category.getName()).isEqualTo("자바자바");
    }


    @Test
    @Transactional
    void update() {
        Optional<Integer> createCategory = service.create(CategoryDto.builder().name("cateogryName").syscode("category_cpp").ord(service.searchLastOrd()).build());
        if(createCategory.isPresent()) {
            Integer id = createCategory.get();

            Category category = service.search(id).get();
            category.change(Syscode.builder().code("category_py").build(), "pythonCat", null, false);

            em.flush();
            em.clear();

            Category findC = service.search(category.getId()).get();
            assertThat(findC.getName()).isEqualTo("pythonCat");
        }
    }

    @Test
    @Transactional
    void delete() {
        List<Category> list = service.searchAll();
        if(list.size() != 0) {
            Category category = list.get(0);

            service.delete(category.getId());
        }
    }

}