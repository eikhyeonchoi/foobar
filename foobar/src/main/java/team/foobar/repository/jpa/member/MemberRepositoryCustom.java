package team.foobar.repository.jpa.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryCustom {
    Optional<Member> findByIdWithFetch(Integer memberId);
    Page<Member> findAllWithFetch(Pageable pageable);

    Optional<Member> findByNicknameWithFetch(String nickname);
    Optional<Member> findByEmailWithFetch(String email);
}
