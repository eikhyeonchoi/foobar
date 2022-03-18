package team.foobar.repository.jpa.banner;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static team.foobar.domain.QBanner.*;


@RequiredArgsConstructor
public class BannerRepositoryImpl implements BannerRepositoryCustom {
    private final JPAQueryFactory factory;

    @Override
    public Integer getLastOrd() {
        return factory.select(banner.ord.max()).from(banner).fetchOne();
    }
}
