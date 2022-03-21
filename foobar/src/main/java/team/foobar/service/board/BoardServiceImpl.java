package team.foobar.service.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Board;
import team.foobar.dto.board.BoardDto;
import team.foobar.repository.jpa.board.BoardRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Primary
public class BoardServiceImpl implements BoardService {

    private final BoardRepository repository;

    @Override
    public Optional<Board> search(Integer id) {
        return repository.findByIdWithFetch(id);
    }

    @Override
    public List<Board> searchAll(Integer page, Integer size) {
        return repository.findAllWithFetch(page, size);
    }

    @Override
    @Transactional
    public Optional<Integer> create(BoardDto dto) {
        Board save = repository.save(dtoToEntity(dto));
        return Optional.of(save.getId());
    }

    @Override
    @Transactional
    public Optional<Integer> update(BoardDto dto) {
        Optional<Board> findBoard = repository.findById(dto.getId());
        if(findBoard.isEmpty()) {
            return Optional.empty();
        }

        Board board = findBoard.get();
        board.change(null, dto.getTitle(), dto.getHtml(), dto.getText(), dto.getView(), dto.getOpenFl(), dto.getFixFl());

        return Optional.of(board.getId());
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Board> searchPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<Board> searchByMemberId(Integer id, Integer page, Integer size) {
        return repository.findByMemberId(id, page, size);
    }

    @Override
    public List<Board> searchByCategoryId(Integer id, Integer page, Integer size) {
        return repository.findByCategoryId(id, page, size);
    }
}
