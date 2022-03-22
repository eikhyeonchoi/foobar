package team.foobar.service.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Board;
import team.foobar.domain.Member;
import team.foobar.dto.board.BoardDto;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    Optional<Board> search(Integer id);

    Page<Board> searchAll(Pageable pageable);
    Optional<Integer> create(BoardDto dto);
    Optional<Integer> update(BoardDto dto);
    void delete(Integer id);
    Page<Board> searchPage(Pageable pageable);
    Page<Board> searchByMemberId(Integer id, Pageable pageable);

    default Board dtoToEntity(BoardDto dto) {
        return Board.builder()
                .id(dto.getId())
                .member(Member.builder().id(dto.getMemberId()).build())
                .title(dto.getTitle())
                .htmlContent(dto.getHtml())
                .textContent(dto.getText())
                .viewCnt(dto.getView())
                .openFl(dto.getOpenFl())
                .fixFl(dto.getFixFl())
                .build();
    }

    default BoardDto entityToDto(Board b) {
        return BoardDto.builder()
                .id(b.getId())
                .memberId(b.getMember().getId())
                .title(b.getTitle())
                .html(b.getHtmlContent())
                .text(b.getTextContent())
                .view(b.getViewCnt())
                .openFl(b.getOpenFl())
                .fixFl(b.getFixFl())
                .build();
    }
}
