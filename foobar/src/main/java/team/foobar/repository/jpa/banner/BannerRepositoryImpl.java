package team.foobar.repository.jpa.banner;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import team.foobar.domain.Banner;

import java.util.List;

import static team.foobar.domain.QBanner.*;


@RequiredArgsConstructor
public class BannerRepositoryImpl implements BannerRepositoryCustom {
    private final JPAQueryFactory factory;

    @Override
    public List<Banner> findAllWithFetch() {
        return factory.selectFrom(banner).fetch();
    }

    @Override
    public Integer getLastOrd() {
        Integer ord = factory.select(banner.ord.max()).from(banner).fetchOne();
        return ord == null ? 1 : ord + 1;
    }
}
