package team.foobar.repository.jpa.file;

import team.foobar.domain.File;

import java.util.List;

public interface FileRepositoryCustom {
    List<File> findByTableAndId(String table, Integer id);
}
