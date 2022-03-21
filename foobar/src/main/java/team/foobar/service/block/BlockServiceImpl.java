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
    public Optional<Block> search(Integer id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Integer> create(BlockDto dto) {
        Block save = repository.save(dtoToEntity(dto));
        return Optional.of(save.getId());
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Block> searchByFromMemberId(Integer id, Integer page, Integer size) {
        return repository.findBlockListByFromMemberId(id, page, size);
    }

    @Override
    @Transactional
    public Long deleteAllByFromMemberId(Integer id) {
        return repository.deleteAllByFromMemberId(id);
    }
}
