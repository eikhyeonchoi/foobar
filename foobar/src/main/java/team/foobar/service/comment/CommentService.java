package team.foobar.service.comment;

import team.foobar.domain.Board;
import team.foobar.domain.Comment;
import team.foobar.domain.Member;
import team.foobar.dto.comment.CommentDto;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> searchByBoardId(Integer id);
    Optional<Integer> create(CommentDto dto);
    Optional<Integer> update(CommentDto dto);
    void delete(Integer id);

    default Comment dtoToEntity(CommentDto dto) {
        return Comment.builder()
                .id(dto.getId())
                .board(Board.builder().id(dto.getBoardId()).build())
                .member(Member.builder().id(dto.getMemberId()).build())
                .tagMember(Member.builder().id(dto.getTagMemberId()).build())
                .content(dto.getContent())
                .parent(Comment.builder().id(dto.getId()).build())
                .build();
    }
}
