package team.foobar.service.banner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Banner;
import team.foobar.domain.Syscode;
import team.foobar.dto.banner.BannerDto;

import java.util.List;
import java.util.Optional;

public interface BannerService {
    Banner search(Integer bannerId);
    List<Banner> searchAll();

    /* syscode 없을 수 있기 때문에 Optional return */
    Optional<Integer> create(BannerDto dto);
    Optional<Integer> update(BannerDto dto);

    void delete(Integer bannerId);
    Page<Banner> searchPage(Pageable pageable);
    Integer findLastOrd();

    default Banner dtoToEntity(BannerDto dto) {
        return Banner.builder()
                .id(dto.getId())
                .positionSys(
                        Syscode.builder().code(dto.getSyscode()).build()
                )
                .name(dto.getName())
                .startDt(dto.getStartDt())
                .endDt(dto.getEndDt())
                .useFl(dto.getUseFl())
                .ord(dto.getOrd())
                .build();
    }

    default BannerDto entityToDto(Banner b) {
        return BannerDto.builder()
                .id(b.getId())
                .syscode(b.getPositionSys().getCode())
                .syscodeValue(b.getPositionSys().getValue())
                .name(b.getName())
                .startDt(b.getStartDt())
                .endDt(b.getEndDt())
                .useFl(b.getUseFl())
                .ord(b.getOrd())
                .build();
    }
}
