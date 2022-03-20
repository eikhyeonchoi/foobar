package team.foobar.block;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Block;
import team.foobar.dto.block.BlockDto;
import team.foobar.service.block.BlockService;
import team.foobar.service.member.MemberService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Test
    @Rollback(false)
    void create() {
        BlockDto blockDto1 = BlockDto.builder().fromId(1).toId(2).build();
        Integer savePk1 = service.create(blockDto1).get();
        Block block = service.search(savePk1).get();

        System.out.println("block = " + block);
        System.out.println("block.getToMember() = " + block.getToMember());
        System.out.println("block.getToMember() = " + block.getToMember());
        
        
//        List<Block> list = service.findByFromMemberId(1);
//        assertThat(list.size()).isEqualTo(2);
//
//        List<Integer> collect = list.stream().map(el -> el.getToMember().getId()).collect(Collectors.toList());
//        assertThat(collect).contains(savePk1, savePk2);
    }

    @Test
    void delete() {
    }

    @Test
    void findByFromMemberId() {
    }
}