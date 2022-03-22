package team.foobar.repository.jpa.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Board;

import java.util.Optional;

public interface BoardRepositoryCustom {
    Optional<Board> findByIdWithFetch(Integer id);
    Page<Board> findAllWithFetch(Pageable pageable);
    Page<Board> findByMemberId(Integer id, Pageable pageable);
}
