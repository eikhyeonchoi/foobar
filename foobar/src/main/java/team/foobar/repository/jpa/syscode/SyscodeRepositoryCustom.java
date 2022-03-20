package team.foobar.repository.jpa.syscode;

import team.foobar.domain.Syscode;

import java.util.List;
import java.util.Optional;

public interface SyscodeRepositoryCustom {
    Optional<Syscode> findByIdWithFetch(String code);
    List<Syscode> findAllWithFetch();
}
