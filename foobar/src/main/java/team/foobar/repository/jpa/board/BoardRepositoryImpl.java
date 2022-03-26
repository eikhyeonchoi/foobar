package team.foobar.repository.jpa.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Board;

import java.util.List;
import java.util.Optional;

import static team.foobar.domain.QBoard.board;
import static team.foobar.domain.QMember.member;
import static team.foobar.domain.QSyscode.syscode;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {
    private final JPAQueryFactory factory;

    @Override
    public Optional<Board> findByIdWithFetch(Integer id) {
        return Optional.ofNullable(factory.selectFrom(board).join(board.member, member).fetchJoin().where(board.id.eq(id)).fetchOne());
    }

    @Override
    public Page<Board> findAllWithFetch(Pageable pageable) {
        List<Board> list = factory.selectFrom(board)
                .join(board.member, member).fetchJoin()
                .join(member.roleSys, syscode).fetchJoin()
                .orderBy(board.fixFl.desc(), board.createDt.desc())
                .fetch();
        return new PageImpl<>(list, pageable, list.size());
    }

    @Override
    public Page<Board> findByMemberId(Integer id, Pageable pageable) {
        List<Board> list = factory.selectFrom(board).join(board.member, member).fetchJoin().where(board.member.id.eq(id)).fetch();
        return new PageImpl<>(list, pageable, list.size());
    }
}
