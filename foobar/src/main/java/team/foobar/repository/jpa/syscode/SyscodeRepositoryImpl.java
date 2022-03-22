package team.foobar.repository.jpa.syscode;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public Page<Syscode> findAllWithFetch(Pageable pageable) {
        List<Syscode> result = factory.selectFrom(syscode).join(syscode.parentSys).fetchJoin().fetch();
        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    public List<Syscode> findAllByParentCode(String parentCode) {
        return factory.selectFrom(syscode).where(syscode.parentSys.code.eq(parentCode)).fetch();
    }
}
