package team.foobar.service.block;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Block;
import team.foobar.repository.jpa.block.BlockRepository;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Primary
public class BlockServiceImpl implements BlockService {
    private final BlockRepository repository;

    @Override
    public Block save(Block block) {
        return repository.save(block);
    }

    @Override
    public void delete(Integer blockId) {
        repository.deleteById(blockId);
    }

    @Override
    public List<Block> findByFromUserId(Integer userId) {
        return repository.getBlockListByFromUserId(userId);
    }
}
