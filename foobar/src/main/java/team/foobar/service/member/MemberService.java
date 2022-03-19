package team.foobar.service.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Member;

import java.util.List;

public interface MemberService {
    Member search(Integer memberId);
    List<Member> searchAll();
    Member create(Member member);
    void delete(Integer memberId);
    Page<Member> searchPage(Pageable pageable);
}
