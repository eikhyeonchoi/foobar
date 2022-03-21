package team.foobar.repository.jpa.board;

import team.foobar.domain.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepositoryCustom {
    Optional<Board> findByIdWithFetch(Integer id);
    List<Board> findAllWithFetch(Integer page, Integer size);
    List<Board> findByMemberId(Integer id, Integer page, Integer size);
    List<Board> findByCategoryId(Integer id, Integer page, Integer size);
}
