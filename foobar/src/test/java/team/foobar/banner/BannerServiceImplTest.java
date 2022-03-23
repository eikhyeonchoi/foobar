package team.foobar.banner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Banner;
import team.foobar.dto.banner.BannerDto;
import team.foobar.service.banner.BannerService;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional(readOnly = true)
class BannerServiceImplTest {

    @Autowired
    BannerService service;

    @Autowired
    EntityManager em;

    @BeforeEach
    void init() {
        BannerDto dto = BannerDto.builder()
                .name("banner")
                .syscode("banner_position_main_header")
                .startDt(LocalDateTime.now())
                .endDt(LocalDateTime.of(2022, 7, 1, 10, 0, 0))
                .ord(service.searchLastOrd())
                .build();
        Integer createdBannerId = service.create(dto).orElse(-1);
    }

    @Test
    void search() {
        Page<Banner> pageResult = service.searchPage(PageRequest.of(0, 100));
        List<Banner> list = pageResult.getContent();
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    @Transactional
    void create() {
        BannerDto dto = BannerDto.builder()
                .name("banner")
                .syscode("banner_position_main_header")
                .startDt(LocalDateTime.now())
                .endDt(LocalDateTime.of(2022, 7, 1, 10, 0, 0))
                .ord(service.searchLastOrd())
                .build();
        Integer createdBannerId = service.create(dto).orElse(-1);

        Banner banner = service.search(createdBannerId).get();

        assertThat(banner.getName()).isEqualTo("banner");
        assertThat(banner.getOrd()).isEqualTo(2);
    }

    @Test
    @Transactional
    void update() {
        BannerDto dto = BannerDto.builder()
                .name("banner")
                .syscode("banner_position_main_header")
                .startDt(LocalDateTime.now())
                .endDt(LocalDateTime.of(2022, 7, 1, 10, 0, 0))
                .ord(service.searchLastOrd())
                .build();
        Integer createdBannerId = service.create(dto).orElse(-1);

        Optional<Banner> search = service.search(createdBannerId);
        if(search.isPresent()) {
            Banner banner = search.get();
            banner.change(null, "bannerUpdate", null, null, true, null);

            em.flush();
            em.clear();

            Banner findBanner = service.search(banner.getId()).get();
            assertThat(findBanner.getName()).isEqualTo("bannerUpdate");
        }
    }

    @Test
    @Transactional
    void delete() {
        Page<Banner> pageResult = service.searchPage(PageRequest.of(0, 100));
        List<Banner> list = pageResult.getContent();
        if(list.size() != 0) {
            Banner banner = list.get(0);
            service.delete(banner.getId());
        } else {
            System.out.println("empty list !!!");
        }
    }
}