package team.foobar.banner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Banner;
import team.foobar.dto.banner.BannerDto;
import team.foobar.service.banner.BannerService;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class BannerServiceImplTest {

    @Autowired
    BannerService service;

    @Test
    void search() {
        List<Banner> list = service.searchAll();

    }

    @Test
    @Rollback(false)
    void create() {
        BannerDto dto = BannerDto.builder()
                .name("banner")
                .syscode("banner_position_main_header")
                .startDt(LocalDateTime.now())
                .endDt(LocalDateTime.of(2022, 7, 1, 10, 0, 0))
                .useFl(true)
                .ord(service.findLastOrd())
                .build();

        Banner banner = service.search(service.create(dto).get());
        assertThat(banner.getName()).isEqualTo("banner");
        assertThat(banner.getOrd()).isEqualTo(1);
        System.out.println("banner = " + banner);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void searchPage() {
    }
}