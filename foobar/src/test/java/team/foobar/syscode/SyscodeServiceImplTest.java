package team.foobar.syscode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Syscode;
import team.foobar.dto.syscode.SyscodeDto;
import team.foobar.service.syscode.SyscodeService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class SyscodeServiceImplTest {
    @Autowired
    SyscodeService service;


    @Test
    void search() {
        Syscode role = service.search("user_role").get();

        System.out.println("role = " + role);
        System.out.println("role.getParentSys().getValue() = " + role.getParentSys().getValue());
    }

    @Test
    void searchAll() {
        List<Syscode> list = service.searchAll();

        for (Syscode syscode : list) {
            System.out.println("syscode = " + syscode);
            if(syscode.getParentSys() != null) {
                System.out.println("syscode.getParentSys().getValue() = " + syscode.getParentSys().getValue());
            }
        }
        assertThat(list.size()).isEqualTo(13);
    }

    @Test
    @Rollback(false)
    @DisplayName("test :: Syscode create")
    void create() {
        Optional<String> s = service.create(
                service.entityToDto(
                        Syscode.builder()
                                .code("bbbb")
                                .parentSys(Syscode.builder().code("root").build())
                                .value("bbbb")
                                .build()
                )
        );

        Syscode search = service.search(s.get()).get();
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