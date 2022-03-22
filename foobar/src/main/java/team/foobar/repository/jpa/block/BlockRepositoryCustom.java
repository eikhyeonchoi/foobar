package team.foobar.repository.jpa.block;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Block;

import java.util.List;
import java.util.Optional;

public interface BlockRepositoryCustom {
    Optional<Block> findByIdWithFetch(Integer id);
    Page<Block> findBlockListByFromMemberId(Integer id, Pageable pageable);
    Long deleteAllByFromMemberId(Integer id);
}
