package team.foobar.repository.jpa.block;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import team.foobar.domain.Block;

import java.util.List;

import static team.foobar.domain.QBlock.block;

@RequiredArgsConstructor
public class BlockRepositoryImpl implements BlockRepositoryCustom {
    private final JPAQueryFactory factory;

    public List<Block> getBlockListByFromUserId(Integer userId) {
        return factory.selectFrom(block).where(block.fromMember.id.eq(userId)).fetch();
    }
}
