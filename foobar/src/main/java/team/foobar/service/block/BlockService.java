package team.foobar.service.block;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Block;
import team.foobar.domain.Member;
import team.foobar.dto.block.BlockDto;

import java.util.List;
import java.util.Optional;

public interface BlockService {
    Optional<Block> search(Integer id);
    Optional<Integer> create(BlockDto dto);
    void delete(Integer id);
    /* page, size 모두 0이면 전체 return */
    Page<Block> searchByFromMemberId(Integer id, Pageable pageable);
    Long deleteAllByFromMemberId(Integer id);

    default Block dtoToEntity(BlockDto dto) {
        return Block.builder()
                .id(dto.getId())
                .fromMember(Member.builder().id(dto.getFromId()).build())
                .toMember(Member.builder().id(dto.getToId()).build())
                .build();
    }

    default BlockDto entityToDto(Block block) {
        return BlockDto.builder()
                .id(block.getId())
                .fromId(block.getFromMember().getId())
                .fromNickname(block.getFromMember().getNickname())
                .toId(block.getToMember().getId())
                .toNickname(block.getToMember().getNickname())
                .build();
    }
}
