package team.foobar.repository.jpa.banner;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import team.foobar.domain.Banner;

import java.util.List;
import java.util.Optional;

import static team.foobar.domain.QBanner.*;
import static team.foobar.domain.QSyscode.*;

@RequiredArgsConstructor
public class BannerRepositoryImpl implements BannerRepositoryCustom {
    private final JPAQueryFactory factory;

    @Override
    public Optional<Banner> findByIdWithFetch(Integer id) {
        return Optional.of(factory.selectFrom(banner).leftJoin(banner.positionSys, syscode).fetchJoin().where(banner.id.eq(id)).fetchOne());
    }

    @Override
    public List<Banner> findAllWithFetch() {
        return factory.selectFrom(banner).leftJoin(banner.positionSys, syscode).fetchJoin().fetch();
    }

    @Override
    public Integer findLastOrd() {
        Integer ord = factory.select(banner.ord.max()).from(banner).fetchOne();
        return ord == null ? 1 : ord + 1;
    }
}
