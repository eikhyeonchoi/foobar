package team.foobar.repository.jpa.comment;

import team.foobar.domain.Comment;

import java.util.List;

public interface CommentRepositoryCustom {
    List<Comment> findByBoardId(Integer id);
}
