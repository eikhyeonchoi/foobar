package team.foobar.repository.jpa.block;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import team.foobar.domain.Block;

import java.util.List;
import java.util.Optional;

import static team.foobar.domain.QBlock.*;
import static team.foobar.domain.QMember.*;

@RequiredArgsConstructor
public class BlockRepositoryImpl implements BlockRepositoryCustom {
    private final JPAQueryFactory factory;

    @Override
    public Optional<Block> findByIdWithFetch(Integer id) {
        return Optional.of(
                factory.selectFrom(block)
                        .leftJoin(block.fromMember, member).fetchJoin()
                        .leftJoin(block.toMember, member).fetchJoin()
                        .fetchOne()
        );
    }

    @Override
    public List<Block> findBlockListByFromMemberId(Integer id, Integer page, Integer size) {
        JPAQuery<Block> q = factory.selectFrom(block)
                .leftJoin(block.fromMember, member).fetchJoin()
                .leftJoin(block.toMember, member).fetchJoin()
                .where(block.fromMember.id.eq(id));

        if(page == 0 && size == 0) {
            return q.fetch();
        } else {
            return q.offset(page).limit(size).fetch();
        }
    }

    @Override
    public Long deleteAllByFromMemberId(Integer id) {
        return factory.delete(block).where(block.fromMember.id.eq(id)).execute();
    }
}
