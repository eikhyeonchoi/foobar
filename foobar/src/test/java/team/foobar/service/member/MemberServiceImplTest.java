package team.foobar.service.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.foobar.domain.Member;
import team.foobar.service.syscode.SyscodeService;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceImplTest {
    @Autowired
    private MemberService service;

    @Autowired
    private SyscodeService syscodeService;

    @Test
    void findOne() {
        Member member = Member.createMember("asd", "asd", syscodeService.findOne("user_role_common"));

        Member save = service.save(member);

        Member findMember = service.findOne(member.getId());

        assertThat(save).isSameAs(findMember);
    }

    @Test
    void findAll() {
        Member member1 = Member.createMember("asd", "asd", syscodeService.findOne("user_role_common"));
        Member member2 = Member.createMember("asd1", "asd1", syscodeService.findOne("user_role_common"));
        Member member3 = Member.createMember("asd2", "asd2", syscodeService.findOne("user_role_common"));
        service.save(member1);
        service.save(member2);
        service.save(member3);

        List<Member> all = service.findAll();

        assertThat(all.size()).isEqualTo(3);
    }

    @Test
    void save() {
    }

    @Test
    void delete() {

        Member member = Member.createMember("asd", "asd", syscodeService.findOne("user_role_common"));

        Member save = service.save(member);

        service.delete(save.getId());

    }
}