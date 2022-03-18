package team.foobar.repository.jpa.block;

import team.foobar.domain.Block;

import java.util.List;

public interface BlockRepositoryCustom {
    List<Block> getBlockListByFromUserId(Integer userId);
}
