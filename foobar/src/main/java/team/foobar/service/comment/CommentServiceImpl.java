package team.foobar.service.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import team.foobar.domain.Board;
import team.foobar.domain.Comment;
import team.foobar.domain.Member;
import team.foobar.dto.comment.CommentDto;
import team.foobar.repository.jpa.board.BoardRepository;
import team.foobar.repository.jpa.comment.CommentRepository;
import team.foobar.repository.jpa.member.MemberRepository;

import javax.print.attribute.standard.PresentationDirection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Primary
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Override
    public List<Comment> searchByBoardId(Integer id) {
        return repository.findByBoardId(id);
    }

    @Override
    public Optional<Comment> search(Integer id) {
        return repository.findByIdWithFetch(id);
    }

    @Override
    public Page<Comment> searchByMemberId(Integer id, Pageable pageable) {
        return repository.findByMemberIdWithFetch(id, pageable);
    }

    @Override
    @Transactional
    public Optional<Integer> create(CommentDto dto) {
        Optional<Board> member = boardRepository.findById(dto.getMemberId());
        Optional<Member> board = memberRepository.findById(dto.getBoardId());
        if(member.isEmpty() || board.isEmpty()) {
            return Optional.empty();
        }

        if(dto.getTagMemberId() != null && memberRepository.findById(dto.getTagMemberId()).isEmpty()) {
            return Optional.empty();
        }

        Comment save = repository.save(dtoToEntity(dto));
        log.info("save = {}", save);
        return Optional.of(save.getId());
    }

    @Override
    @Transactional
    public Optional<Integer> update(CommentDto dto) {
        Optional<Comment> search = repository.findById(dto.getId());
        if(search.isEmpty()) {
            return Optional.empty();
        }

        Comment comment = search.get();
        comment.change(
                dto.getTagMemberId() != null ? Member.builder().id(dto.getTagMemberId()).build() : null,
                dto.getContent(),
                null
        );

        return Optional.of(comment.getId());
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Optional<Comment> search = repository.findByIdWithFetch(id);
        if(search.isEmpty()) {
            return;
        }

        /* 자식댓글이면 삭제 */
        /* 부모댓글이면 자식있으면 deleteFl 변경, 없으면 그냥 삭제 */
        Comment comment = search.get();
        if(comment.getParent() != null) {
            repository.deleteById(comment.getId());
        } else {
            List<Comment> childList = repository.findChildById(id);

            if(childList.size() == 0) {
                repository.deleteById(id);
            } else {
                comment.change(null, null, true);
            }
        }
    }
}
