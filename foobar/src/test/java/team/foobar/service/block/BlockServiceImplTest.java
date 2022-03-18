package team.foobar.service.block;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Block;
import team.foobar.domain.Member;
import team.foobar.domain.Syscode;
import team.foobar.repository.jpa.member.MemberRepository;
import team.foobar.service.member.MemberService;
import team.foobar.service.syscode.SyscodeService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class BlockServiceImplTest {

    @Autowired
    BlockService service;

    @Autowired
    MemberService memberService;

    @Autowired
    SyscodeService syscodeService;

    @Test
    void save() {
        Syscode rootCode = Syscode.createRootCode();
        syscodeService.save(rootCode);

        Member member1 = Member.createMember("1", "1", rootCode);
        Member member2 = Member.createMember("2", "2", rootCode);
        Member s1 = memberService.save(member1);
        Member s2 = memberService.save(member2);

        Block block = Block.createBlock(s1, s2);
        Block save = service.save(block);


        assertThat(save.getFromMember()).isSameAs(s1);
        assertThat(save.getToMember()).isSameAs(s2);
    }

    @Test
    void delete() {
        Syscode rootCode = Syscode.createRootCode();
        syscodeService.save(rootCode);

        Member member1 = Member.createMember("1", "1", rootCode);
        Member member2 = Member.createMember("2", "2", rootCode);
        Member s1 = memberService.save(member1);
        Member s2 = memberService.save(member2);

        Block block = Block.createBlock(s1, s2);
        Block save = service.save(block);

        service.delete(save.getId());
    }

    @Test
    void findByFromUserId() {
        Syscode rootCode = Syscode.createRootCode();
        syscodeService.save(rootCode);

        Member member1 = Member.createMember("1", "1", rootCode);
        Member member2 = Member.createMember("2", "2", rootCode);
        Member member3 = Member.createMember("3", "3", rootCode);
        Member member4 = Member.createMember("4", "4", rootCode);
        Member member5 = Member.createMember("5", "5", rootCode);

        Member s1 = memberService.save(member1);
        Member s2 = memberService.save(member2);
        Member s3 = memberService.save(member3);
        Member s4 = memberService.save(member4);
        Member s5 = memberService.save(member5);

        Block block1 = Block.createBlock(s1, s2);
        Block block2 = Block.createBlock(s1, s3);
        Block block3 = Block.createBlock(s1, s4);
        Block block4 = Block.createBlock(s1, s5);

        Block save1 = service.save(block1);
        Block save2 = service.save(block2);
        Block save3 = service.save(block3);
        Block save4 = service.save(block4);

        List<Block> blockedByS1 = service.findByFromUserId(s1.getId());
        for (Block block : blockedByS1) {
            System.out.println("block = " + block.getToMember().getEmail());
        }

        assertThat(blockedByS1.size()).isEqualTo(4);
    }
}