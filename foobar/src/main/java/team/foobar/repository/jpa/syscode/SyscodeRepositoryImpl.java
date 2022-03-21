package team.foobar.repository.jpa.syscode;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import team.foobar.domain.Syscode;

import java.util.List;
import java.util.Optional;

import static team.foobar.domain.QSyscode.*;

@RequiredArgsConstructor
public class SyscodeRepositoryImpl implements SyscodeRepositoryCustom {
    private final JPAQueryFactory factory;

    @Override
    public Optional<Syscode> findByIdWithFetch(String code) {
        return Optional.of(factory.selectFrom(syscode).where(syscode.code.eq(code)).fetchOne());
    }

    @Override
    public List<Syscode> findAllWithFetch(Integer page, Integer size) {
        JPAQuery<Syscode> query = factory.selectFrom(syscode).join(syscode.parentSys).fetchJoin();

        if(page == 0 && size == 0) {
            return query.fetch();
        } else {
            return query.offset(page).limit(size).fetch();
        }
    }

    @Override
    public List<Syscode> findAllByParentCode(String parentCode) {
        return factory.selectFrom(syscode).join(syscode.parentSys).fetchJoin().where(syscode.parentSys.code.eq(parentCode)).fetch();
    }
}
