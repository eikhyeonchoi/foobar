package team.foobar.service.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Board;
import team.foobar.domain.Comment;
import team.foobar.domain.Member;
import team.foobar.dto.comment.CommentDto;
import team.foobar.repository.jpa.board.BoardRepository;
import team.foobar.repository.jpa.comment.CommentRepository;
import team.foobar.repository.jpa.member.MemberRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
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
                dto.getContent()
        );

        return Optional.of(comment.getId());
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
