package team.foobar.service.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Category;
import team.foobar.domain.Syscode;
import team.foobar.dto.category.CategoryDto;
import team.foobar.repository.jpa.category.CategoryRepository;
import team.foobar.repository.jpa.syscode.SyscodeRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Primary
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final SyscodeRepository syscodeRepository;

    @Override
    public Optional<Category> search(Integer id) {
        return repository.findByIdWithFetch(id);
    }

    @Override
    public List<Category> searchAll() {
        return repository.findAllWithFetch();
    }

    @Override
    @Transactional
    public Optional<Integer> create(CategoryDto dto) {
        Optional<Syscode> syscode = syscodeRepository.findById(dto.getSyscode());
        if(syscode.isEmpty()) {
            return Optional.empty();
        }

        Category save = repository.save(dtoToEntity(dto));
        return Optional.of(save.getId());
    }

    @Override
    @Transactional
    public Optional<Integer> update(CategoryDto dto) {
        Optional<Category> category = repository.findById(dto.getId());
        if(category.isEmpty()) {
            return Optional.empty();
        }

        Category c = category.get();
        c.change(
                Syscode.builder().code(dto.getSyscode()).build(),
                dto.getName(),
                dto.getOrd(),
                dto.getUseFl()
        );

        return Optional.of(c.getId());
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Integer searchLastOrd() {
        Integer max = repository.findLastOrd();
        return max == null ? 1 : max + 1;
    }
}
