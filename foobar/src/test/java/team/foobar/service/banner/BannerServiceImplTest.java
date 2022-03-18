package team.foobar.service.banner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.foobar.domain.Banner;
import team.foobar.service.syscode.SyscodeService;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class BannerServiceImplTest {
    @Autowired
    BannerService service;

    @Autowired
    SyscodeService syscodeService;


    @Test
    void findOne() {
        LocalDateTime st = LocalDateTime.of(2022, 3, 10, 10, 0, 0);
        LocalDateTime end = LocalDateTime.of(2022, 4, 10, 10, 0, 0);
        Banner banner = Banner.createBanner(syscodeService.findOne("banner_position"), "메인헤더", st, end, 0);
        Banner saveBanner = service.save(banner);

        Banner findBanner = service.findOne(saveBanner.getId());

        assertThat(saveBanner).isSameAs(findBanner);
    }

    @Test
    void findAll() {
        LocalDateTime st = LocalDateTime.of(2022, 3, 10, 10, 0, 0);
        LocalDateTime end = LocalDateTime.of(2022, 4, 10, 10, 0, 0);
        Banner banner1 = Banner.createBanner(syscodeService.findOne("banner_position_main_header"), "메인헤더", st, end, 0);
        Banner banner2 = Banner.createBanner(syscodeService.findOne("banner_position_main_header"), "메인헤더", st, end, 0);
        Banner banner3 = Banner.createBanner(syscodeService.findOne("banner_position_main_header"), "메인헤더", st, end, 0);

        service.save(banner1);
        service.save(banner2);
        service.save(banner3);

        List<Banner> all = service.findAll();

        assertThat(all.size()).isEqualTo(3);
    }


    @Test
    void delete() {
        LocalDateTime st = LocalDateTime.of(2022, 3, 10, 10, 0, 0);
        LocalDateTime end = LocalDateTime.of(2022, 4, 10, 10, 0, 0);
        Banner banner = Banner.createBanner(syscodeService.findOne("banner_position"), "메인헤더", st, end, 0);
        Banner saveBanner = service.save(banner);

        service.delete(saveBanner.getId());
    }

    @Test
    void getLastOrd() {
        assertThat(service.getLastOrd()).isEqualTo(1);
    }
}