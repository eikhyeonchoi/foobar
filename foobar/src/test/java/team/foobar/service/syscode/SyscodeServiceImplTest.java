package team.foobar.service.syscode;

import org.hibernate.TransientPropertyValueException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Syscode;
import team.foobar.dto.SyscodeDto;
import team.foobar.exception.ObjectNotFoundException;
import team.foobar.repository.jpa.syscode.SyscodeRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class SyscodeServiceImplTest {
    @Autowired
    SyscodeService service;


    @Test
    void searchAll() {
        List<Syscode> list = service.searchAll();
        assertThat(list.size()).isEqualTo(13);
    }

    @Test
    @Rollback(false)
    @DisplayName("test :: Syscode create")
    void create() {
        Syscode syscode = service.create(
                service.EntityToDto(
                        Syscode.builder()
                        .code("bbbb")
                        .parentSys(Syscode.builder().code("root").build())
                        .value("bbbb")
                        .build()
                )
        );

        Syscode search = service.search(syscode.getCode());
    }

    @Test
    @Rollback(value = false)
    void update() {
        SyscodeDto dto = SyscodeDto.builder().code("category_notice").parentCode("1111").value("수정테스트").build();
        assertThat(service.update(dto)).isEqualTo(-1);
    }

    @Test
    void delete() {
    }

    @Test
    void searchPage() {
    }
}