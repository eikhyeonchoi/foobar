package team.foobar.repository.jpa.banner;

import org.springframework.data.jpa.repository.JpaRepository;
import team.foobar.domain.Banner;

public interface BannerRepository extends JpaRepository<Banner, Integer>, BannerRepositoryCustom {
}
