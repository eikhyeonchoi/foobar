package team.foobar.repository.jpa.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import team.foobar.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>, CommentRepositoryCustom {
}
