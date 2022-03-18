package team.foobar.service.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Member;

import java.util.List;

public interface MemberService {
    Member findOne(Integer memberId);
    List<Member> findAll();
    Member save(Member member);
    void delete(Integer memberId);
    Page<Member> findAllPage(Pageable pageable);
}
