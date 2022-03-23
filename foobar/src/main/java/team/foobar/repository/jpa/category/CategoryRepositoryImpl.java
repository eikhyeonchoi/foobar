package team.foobar.repository.jpa.category;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import team.foobar.domain.Category;

import java.util.List;
import java.util.Optional;

import static team.foobar.domain.QCategory.*;
import static team.foobar.domain.QSyscode.*;

@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom{
    private final JPAQueryFactory factory;

    @Override
    public Optional<Category> findByIdWithFetch(Integer id) {
        return Optional.ofNullable(factory.selectFrom(category).join(category.typeSys, syscode).where(category.id.eq(id)).fetchJoin().fetchOne());
    }

    @Override
    public List<Category> findAllWithFetch() {
        return factory.selectFrom(category).join(category.typeSys, syscode).fetchJoin().fetch();
    }

    @Override
    public Integer findLastOrd() {
        return factory.select(category.ord.max()).from(category).fetchOne();
    }
}
