package team.foobar.repository.jpa.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public Page<Member> findAllWithFetch(Pageable pageable) {
        List<Member> list = factory.selectFrom(member).join(member.roleSys, syscode).fetchJoin().fetch();
        return new PageImpl<>(list, pageable, list.size());
    }

    @Override
    public Optional<Member> findByNicknameWithFetch(String nickname) {
        return Optional.of(factory.selectFrom(member).join(member.roleSys, syscode).fetchJoin().where(member.nickname.eq(nickname)).fetchOne());
    }

    @Override
    public Optional<Member> findByEmailWithFetch(String email) {
        return Optional.of(factory.selectFrom(member).join(member.roleSys, syscode).fetchJoin().where(member.email.eq(email)).fetchOne());
    }
}
