package team.foobar.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Member;
import team.foobar.domain.Syscode;
import team.foobar.dto.member.MemberDto;
import team.foobar.service.member.MemberService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    MemberService service;

    @Test
    void searchAll() {
        List<Member> members = service.searchAll();
        assertThat(members.size()).isEqualTo(10);
    }

    @Test
    void search() {
        List<Member> list = service.searchAll();
        for (Member member : list) {
            System.out.println("member = " + member);
            System.out.println("member.getRoleSys().getValue() = " + member.getRoleSys().getValue());
        }
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
        Optional<Member> member = service.getByNickname("nickname0");
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