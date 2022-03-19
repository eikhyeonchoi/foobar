package team.foobar.service.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Board;

import java.util.List;

public interface BoardService {
    public Board search(Integer boardId);
    public List<Board> searchAll();
    public Board create(Board board);
//    public void update();
    public void delete(Integer boardId);
    public Page<Board> searchPage(Pageable pageable);
    public List<Board> findByMember(Integer boardId);
}
