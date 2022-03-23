package team.foobar.service.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Board;
import team.foobar.domain.Comment;
import team.foobar.domain.Member;
import team.foobar.dto.comment.CommentDto;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> searchByBoardId(Integer id);
    Optional<Comment> search(Integer id);
    Page<Comment> searchByMemberId(Integer id, Pageable pageable);
    Optional<Integer> create(CommentDto dto);
    Optional<Integer> update(CommentDto dto);
    void delete(Integer id);

    default Comment dtoToEntity(CommentDto dto) {
        Comment.CommentBuilder builder = Comment.builder()
                .id(dto.getId())
                .board(Board.builder().id(dto.getBoardId()).build())
                .member(Member.builder().id(dto.getMemberId()).build())
                .content(dto.getContent());

        if(dto.getParentId() != null) {
            builder.parent(Comment.builder().id(dto.getParentId()).build());
        }

        if(dto.getTagMemberId() != null) {
            builder.tagMember(Member.builder().id(dto.getTagMemberId()).build());
        }

        return builder.build();
    }
}
