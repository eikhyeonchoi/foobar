package team.foobar.repository.jpa.board;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import team.foobar.domain.Board;
import team.foobar.domain.QBoard;

import java.util.List;
import java.util.Optional;

import static team.foobar.domain.QBoard.*;
import static team.foobar.domain.QMember.*;
import static team.foobar.domain.QCategoryBoard.*;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {
    private final JPAQueryFactory factory;

    @Override
    public Optional<Board> findByIdWithFetch(Integer id) {
        return Optional.of(factory.selectFrom(board).leftJoin(board.member, member).fetchJoin().where(board.id.eq(id)).fetchOne());
    }

    @Override
    public List<Board> findAllWithFetch(Integer page, Integer size) {
        JPAQuery<Board> q = factory.selectFrom(board).leftJoin(board.member, member).fetchJoin();
        if(page == 0 && size == 0) {
            return q.fetch();
        } else {
            return q.offset(page).limit(size).fetch();
        }
    }

    @Override
    public List<Board> findByMemberId(Integer id, Integer page, Integer size) {
        JPAQuery<Board> q = factory.selectFrom(board).leftJoin(board.member, member).fetchJoin().where(board.member.id.eq(id));
        if(page == 0 && size == 0) {
            return q.fetch();
        } else {
            return q.offset(page).limit(size).fetch();
        }
    }

    @Override
    public List<Board> findByCategoryId(Integer id, Integer page, Integer size) {
        JPAQuery<Board> q = factory.selectFrom(board).leftJoin(categoryBoard.board).fetchJoin().where(categoryBoard.category.id.eq(id));
        if(page == 0 && size == 0) {
            return q.fetch();
        } else {
            return q.offset(page).limit(size).fetch();
        }
    }
}
