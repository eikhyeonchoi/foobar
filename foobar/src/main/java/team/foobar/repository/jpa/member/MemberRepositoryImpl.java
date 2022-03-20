package team.foobar.repository.jpa.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import team.foobar.domain.Member;

import java.util.List;
import java.util.Optional;

import static team.foobar.domain.QMember.*;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory factory;

    @Override
    public List<Member> findAllWithFetch() {
        return factory.selectFrom(member).fetch();
    }
}
