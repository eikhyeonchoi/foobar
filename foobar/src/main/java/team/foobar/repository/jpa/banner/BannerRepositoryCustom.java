package team.foobar.repository.jpa.banner;

import team.foobar.domain.Banner;

import java.util.List;

public interface BannerRepositoryCustom {
    List<Banner> findAllWithFetch();
    Integer getLastOrd();
}
