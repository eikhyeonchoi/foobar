package team.foobar.repository.jpa.banner;

import team.foobar.domain.Banner;

import java.util.List;
import java.util.Optional;

public interface BannerRepositoryCustom {
    Optional<Banner> findByIdWithFetch(Integer id);
    List<Banner> findAllWithFetch();
    Integer findLastOrd();
}
