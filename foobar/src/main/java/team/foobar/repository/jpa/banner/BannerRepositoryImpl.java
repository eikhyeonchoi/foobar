package team.foobar.repository.jpa.banner;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
        return Optional.ofNullable(factory.selectFrom(banner).join(banner.positionSys, syscode).fetchJoin().where(banner.id.eq(id)).fetchOne());
    }

    @Override
    public Page<Banner> findAllWithFetch(Pageable pageable) {
        List<Banner> list = factory.selectFrom(banner)
                .join(banner.positionSys, syscode).fetchJoin()
                .where(banner.useFl.eq(Boolean.valueOf("1")))
                .orderBy(banner.ord.asc())
                .fetch();
        return new PageImpl<>(list, pageable, list.size());
    }

    @Override
    public Integer findLastOrd() {
        Integer ord = factory.select(banner.ord.max()).from(banner).fetchOne();
        return ord == null ? 1 : ord + 1;
    }
}
