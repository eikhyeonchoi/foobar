package team.foobar.repository.jpa.member;

import team.foobar.domain.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findAllWithFetch();
}
