package team.foobar.service.file;


import team.foobar.domain.File;
import team.foobar.dto.file.FileDto;

import java.util.List;
import java.util.Optional;

public interface FileService {
    Optional<Integer> create(FileDto dto);
    void delete(Integer id);
    List<File> searchByTableAndId(String table, Integer id);

    default File dtoToEntity(FileDto dto) {
        return File.builder().targetTable(dto.getTTable()).targetId(dto.getTId()).context(dto.getContext()).build();
    }
}
