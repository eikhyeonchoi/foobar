package team.foobar.repository.jpa.block;

import org.springframework.data.jpa.repository.JpaRepository;
import team.foobar.domain.Block;

public interface BlockRepository extends JpaRepository<Block, Integer>, BlockRepositoryCustom {
}
