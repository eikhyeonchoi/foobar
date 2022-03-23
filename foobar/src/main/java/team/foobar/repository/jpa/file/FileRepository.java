package team.foobar.repository.jpa.file;

import org.springframework.data.jpa.repository.JpaRepository;
import team.foobar.domain.File;

public interface FileRepository extends JpaRepository<File, Integer>, FileRepositoryCustom {
}
