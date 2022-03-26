package team.foobar.service.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Board;
import team.foobar.domain.CategoryBoard;
import team.foobar.domain.Member;
import team.foobar.dto.board.BoardDto;
import team.foobar.repository.jpa.board.BoardRepository;
import team.foobar.repository.jpa.member.MemberRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Primary
public class BoardServiceImpl implements BoardService {

    private final BoardRepository repository;
    private final MemberRepository memberRepository;

    @Override
    public Optional<Board> search(Integer id) {
        return repository.findByIdWithFetch(id);
    }

    @Override
    public Page<Board> searchAll(Pageable pageable) {
        return repository.findAllWithFetch(pageable);
    }

    @Override
    @Transactional
    public Optional<Integer> create(BoardDto dto) {
        Optional<Member> member = memberRepository.findById(dto.getMemberId());
        if(member.isEmpty()) {
            return Optional.empty();
        }

        Board save = repository.save(dtoToEntity(dto));
        return Optional.of(save.getId());
    }

    @Override
    @Transactional
    public Optional<Integer> update(BoardDto dto) {
        Optional<Board> findBoard = repository.findById(dto.getId());
        if(findBoard.isEmpty()) {
            return Optional.empty();
        }

        Board board = findBoard.get();
        board.change(null, dto.getTitle(), dto.getHtml(), dto.getText(), dto.getView(), dto.getOpenFl(), dto.getFixFl());

        return Optional.of(board.getId());
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Board> searchByMemberId(Integer id, Pageable pageable) {
        return repository.findByMemberId(id, pageable);
    }
}
