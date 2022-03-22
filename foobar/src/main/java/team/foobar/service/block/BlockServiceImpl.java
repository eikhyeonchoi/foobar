package team.foobar.service.block;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Block;
import team.foobar.domain.Member;
import team.foobar.dto.block.BlockDto;
import team.foobar.repository.jpa.block.BlockRepository;
import team.foobar.repository.jpa.member.MemberRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Primary
public class BlockServiceImpl implements BlockService {
    private final BlockRepository repository;
    private final MemberRepository memberRepository;

    @Override
    public Optional<Block> search(Integer id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Integer> create(BlockDto dto) {
        Optional<Member> fMember = memberRepository.findById(dto.getFromId());
        Optional<Member> tMember = memberRepository.findById(dto.getToId());
        if(fMember.isEmpty() || tMember.isEmpty()) {
            return Optional.empty();
        }

        Block save = repository.save(dtoToEntity(dto));
        return Optional.of(save.getId());
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Block> searchByFromMemberId(Integer id, Pageable pageable) {
        return repository.findBlockListByFromMemberId(id, pageable);
    }

    @Override
    @Transactional
    public Long deleteAllByFromMemberId(Integer id) {
        return repository.deleteAllByFromMemberId(id);
    }
}
