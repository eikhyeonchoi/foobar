package team.foobar.repository.jpa.syscode;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Syscode;

import java.util.List;
import java.util.Optional;

public interface SyscodeRepositoryCustom {
    Optional<Syscode> findByIdWithFetch(String code);
    Page<Syscode> findAllWithFetch(Pageable pageable);
    List<Syscode> findAllByParentCode(String parentCode);
}
