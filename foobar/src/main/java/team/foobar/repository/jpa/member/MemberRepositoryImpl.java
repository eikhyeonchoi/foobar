package team.foobar.repository.jpa.member;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import team.foobar.domain.Member;

import java.util.List;
import java.util.Optional;

import static team.foobar.domain.QMember.*;
import static team.foobar.domain.QSyscode.*;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory factory;

    @Override
    public Optional<Member> findByIdWithFetch(Integer memberId) {
        return Optional.of(factory.selectFrom(member).join(member.roleSys, syscode).fetchJoin().where(member.id.eq(memberId)).fetchOne());
    }

    @Override
    public List<Member> findAllWithFetch(Integer page, Integer size) {
        JPAQuery<Member> q = factory.selectFrom(member).join(member.roleSys, syscode).fetchJoin();
        if(page == 0 && size ==  0) {
            return q.fetch();
        } else {
            return q.offset(page).limit(size).fetch();
        }
    }
}
