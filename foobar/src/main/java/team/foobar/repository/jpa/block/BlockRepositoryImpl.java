package team.foobar.repository.jpa.block;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
                        .join(block.fromMember, member).fetchJoin()
                        .join(block.toMember, member).fetchJoin()
                        .fetchOne()
        );
    }

    @Override
    public Page<Block> findBlockListByFromMemberId(Integer id, Pageable pageable) {
        List<Block> list = factory.selectFrom(block)
                .join(block.fromMember, member).fetchJoin()
                .join(block.toMember, member).fetchJoin()
                .where(block.fromMember.id.eq(id)).fetch();

        return new PageImpl<>(list, pageable, list.size());
    }

    @Override
    public Long deleteAllByFromMemberId(Integer id) {
        return factory.delete(block).where(block.fromMember.id.eq(id)).execute();
    }
}
