package team.foobar.repository.jpa.member;

import org.springframework.data.jpa.repository.JpaRepository;
import team.foobar.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>, MemberRepositoryCustom {
}
