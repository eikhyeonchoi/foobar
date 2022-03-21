package team.foobar.service.category;

import team.foobar.domain.Category;
import team.foobar.domain.Syscode;
import team.foobar.dto.category.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> search(Integer id);
    List<Category> searchAll();
    Optional<Integer> create(CategoryDto dto);
    Optional<Integer> update(CategoryDto dto);
    void delete(Integer id);
    Integer searchLastOrd();

    default Category dtoToEntity(CategoryDto dto) {
        return Category.builder()
                .id(dto.getId())
                .typeSys(Syscode.builder().code(dto.getSyscode()).build())
                .name(dto.getName())
                .ord(dto.getOrd())
                .useFl(dto.getUseFl())
                .build();
    }

    default CategoryDto entityToDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .syscode(category.getTypeSys().getCode())
                .syscodeName(category.getTypeSys().getValue())
                .name(category.getName())
                .ord(category.getOrd())
                .useFl(category.getUseFl())
                .build();
    }
}
