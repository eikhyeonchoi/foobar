package team.foobar.repository.jpa.board;

import org.springframework.data.jpa.repository.JpaRepository;
import team.foobar.domain.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer>, BoardRepositoryCustom {
}
