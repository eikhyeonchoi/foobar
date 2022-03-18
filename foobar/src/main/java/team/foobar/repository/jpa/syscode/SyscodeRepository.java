package team.foobar.repository.jpa.syscode;

import org.springframework.data.jpa.repository.JpaRepository;
import team.foobar.domain.Syscode;

public interface SyscodeRepository extends JpaRepository<Syscode, String>, SyscodeRepositoryCustom {
}
