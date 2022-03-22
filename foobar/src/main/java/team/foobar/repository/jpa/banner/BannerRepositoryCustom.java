package team.foobar.repository.jpa.banner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Banner;

import java.util.List;
import java.util.Optional;

public interface BannerRepositoryCustom {
    Optional<Banner> findByIdWithFetch(Integer id);
    Page<Banner> findAllWithFetch(Pageable pageable);
    Integer findLastOrd();
}
