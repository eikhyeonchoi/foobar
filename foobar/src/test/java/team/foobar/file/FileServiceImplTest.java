package team.foobar.file;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.File;
import team.foobar.dto.file.FileDto;
import team.foobar.service.file.FileService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional(readOnly = true)
class FileServiceImplTest {
    @Autowired
    FileService fileService;

    @Test
    @Transactional
    void create() {
        for (int i = 0; i < 10; i++) {
            FileDto dto = FileDto.builder().tTable("board").tId(1).context("context" + i).build();
            fileService.create(dto);
        }


        List<File> board = fileService.searchByTableAndId("board", 1);
        assertThat(board.size()).isEqualTo(10);

        fileService.delete(1);
    }
}