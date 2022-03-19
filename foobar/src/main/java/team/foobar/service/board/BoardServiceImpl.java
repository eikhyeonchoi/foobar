package team.foobar.service.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Board;
import team.foobar.repository.jpa.board.BoardRepository;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Primary
public class BoardServiceImpl implements BoardService {

    private final BoardRepository repository;

    @Override
    public Board search(Integer boardId) {
        return repository.findById(boardId).orElse(null);
    }

    @Override
    public List<Board> searchAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Board create(Board board) {
        return repository.save(board);
    }

    @Override
    @Transactional
    public void delete(Integer boardId) {
        repository.deleteById(boardId);
    }

    @Override
    public Page<Board> searchPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<Board> findByMember(Integer memberId) {
        return null;
    }
}
