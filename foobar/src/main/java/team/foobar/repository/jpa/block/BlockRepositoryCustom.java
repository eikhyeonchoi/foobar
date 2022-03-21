package team.foobar.repository.jpa.block;

import team.foobar.domain.Block;

import java.util.List;
import java.util.Optional;

public interface BlockRepositoryCustom {
    Optional<Block> findByIdWithFetch(Integer id);
    List<Block> findBlockListByFromMemberId(Integer id, Integer page, Integer size);
    Long deleteAllByFromMemberId(Integer id);
}
