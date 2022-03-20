package team.foobar.service.block;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Block;
import team.foobar.dto.block.BlockDto;
import team.foobar.repository.jpa.block.BlockRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Primary
public class BlockServiceImpl implements BlockService {
    private final BlockRepository repository;

    @Override
    public Optional<Block> search(Integer blockId) {
        return repository.findById(blockId);
    }

    @Override
    public Optional<Integer> create(BlockDto dto) {
        Block save = repository.save(dtoToEntity(dto));
        return Optional.of(save.getId());
    }

    @Override
    public void delete(Integer blockId) {
        repository.deleteById(blockId);
    }

    @Override
    public List<Block> findByFromMemberId(Integer userId) {
        return repository.getBlockListByFromMemberId(userId);
    }
}
