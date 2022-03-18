package team.foobar.service.banner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Banner;

import java.util.List;

public interface BannerService {
    Banner findOne(Integer bannerId);
    List<Banner> findAll();
    Banner save(Banner member);
    void delete(Integer bannerId);
    Page<Banner> findAllPage(Pageable pageable);
    Integer getLastOrd();
}
