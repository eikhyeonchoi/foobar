package team.foobar.service.banner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Banner;
import team.foobar.domain.Syscode;
import team.foobar.dto.BannerDto;

import java.util.List;

public interface BannerService {
    Banner search(Integer bannerId);
    List<Banner> searchPage();
    Banner create(BannerDto dto);
    void delete(Integer bannerId);
    Page<Banner> searchPage(Pageable pageable);
    Integer findLastOrd();

    default Banner DtoToEntity(BannerDto dto) {
//        return Banner.builder()
//                .id(dto.getId())
//                .positionSys(
//
//                )
        return null;
    }
}
