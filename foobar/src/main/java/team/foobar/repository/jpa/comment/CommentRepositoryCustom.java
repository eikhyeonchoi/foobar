package team.foobar.repository.jpa.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepositoryCustom {
    List<Comment> findByBoardId(Integer id);
    Optional<Comment> findByIdWithFetch(Integer id);
    Page<Comment> findByMemberIdWithFetch(Integer id, Pageable pageable);
    List<Comment> findChildById(Integer parentId);
}
