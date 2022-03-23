package team.foobar.service.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.File;
import team.foobar.dto.file.FileDto;
import team.foobar.repository.jpa.file.FileRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Primary
public class FileServiceImpl implements FileService {
    private final FileRepository repository;

    @Override
    public Optional<Integer> create(FileDto dto) {
        File save = repository.save(dtoToEntity(dto));
        return Optional.of(save.getId());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<File> searchByTableAndId(String table, Integer id) {
        return repository.findByTableAndId(table, id);
    }
}
