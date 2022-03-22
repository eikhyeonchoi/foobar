package team.foobar.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Member;
import team.foobar.dto.member.MemberDto;
import team.foobar.service.member.MemberService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    MemberService service;

    @Test
    void searchAll() {
        Page<Member> pageResult = service.searchPage(PageRequest.of(0, 100));
        List<Member> list = pageResult.getContent();
    }

    @Test
    void search() {
        Member member = service.search(1).get();

        System.out.println("member = " + member);
        System.out.println("member.getRoleSys().getValue() = " + member.getRoleSys().getValue());

        assertThat(member.getNickname()).isEqualTo("nickname0");
    }

    @Test
    void create() {
        MemberDto dto = MemberDto.builder()
                .syscode("user_role_common")
                .email("test")
                .pwd("a")
                .nickname("a")
                .token("a")
                .build();

        Integer saveUser = service.create(dto).get();

        Member member = service.search(saveUser).orElse(null);
        System.out.println(member);
    }

    @Test
    void nickname() {
        Optional<Member> member = service.searchByNickname("nickname0");
        if(member.isPresent()) {
            System.out.println("member = " + member);
            assertThat(member.get().getId()).isEqualTo(1);
        } else {
            System.out.println("empty");
            assertThat(member).isEqualTo(Optional.empty());
        }
    }

    @Test
    void delete() {
        MemberDto dto = MemberDto.builder()
                .syscode("user_role_common")
                .email("test")
                .pwd("a")
                .nickname("a")
                .token("a")
                .build();
        Integer saveUser = service.create(dto).get();
        service.delete(saveUser);
    }
}