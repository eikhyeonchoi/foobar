package team.foobar.repository.jpa.member;

import team.foobar.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryCustom {
    Optional<Member> findByIdWithFetch(Integer memberId);
    List<Member> findAllWithFetch(Integer page, Integer size);
}
