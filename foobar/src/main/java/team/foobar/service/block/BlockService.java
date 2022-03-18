package team.foobar.service.block;

import team.foobar.domain.Block;

import java.util.List;

public interface BlockService {
    Block save(Block block);
    void delete(Integer blockId);
    List<Block> findByFromUserId(Integer userId);
}
