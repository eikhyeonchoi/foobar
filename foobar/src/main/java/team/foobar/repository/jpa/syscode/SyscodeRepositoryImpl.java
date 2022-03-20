package team.foobar.repository.jpa.syscode;

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
        return Optional.empty();
    }

    @Override
    public List<Syscode> findAllWithFetch() {
        return factory.selectFrom(syscode).leftJoin(syscode.parentSys).fetchJoin().fetch();
    }
}
