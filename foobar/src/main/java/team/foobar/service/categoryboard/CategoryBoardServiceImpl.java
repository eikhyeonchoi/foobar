package team.foobar.service.categoryboard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.CategoryBoard;
import team.foobar.dto.categoryboard.CategoryBoardDto;
import team.foobar.repository.jpa.board.BoardRepository;
import team.foobar.repository.jpa.category.CategoryRepository;
import team.foobar.repository.jpa.categoryboard.CategoryBoardRepository;
import team.foobar.repository.jpa.categoryboard.CategoryBoardRepositoryCustom;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Primary
public class CategoryBoardServiceImpl implements CategoryBoardService {
    private final CategoryBoardRepository repository;
    private final CategoryRepository categoryRepository;
    private final BoardRepository boardRepository;

    @Override
    public Optional<Integer> create(CategoryBoardDto dto) {
        if(categoryRepository.findById(dto.getCategoryId()).isEmpty()) {
            return Optional.empty();
        }

        if(boardRepository.findById(dto.getBoardId()).isEmpty()) {
            return Optional.empty();
        }

        CategoryBoard save = repository.save(dtoToEntity(dto));
        return Optional.of(save.getId());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
