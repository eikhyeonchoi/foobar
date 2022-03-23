package team.foobar.block;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Block;
import team.foobar.dto.block.BlockDto;
import team.foobar.service.block.BlockService;
import team.foobar.service.member.MemberService;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Transactional(readOnly = true)
class BlockServiceImplTest {
    @Autowired
    BlockService service;

    @Autowired
    MemberService memberService;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void update() {
        BlockDto blockDto1 = BlockDto.builder().fromId(1).toId(2).build();
        Integer pk = service.create(blockDto1).get();

        Block block = service.search(pk).get();
        block.change(null, memberService.search(3).get());

        em.flush();
        em.clear();

        Page<Block> pageResult = service.searchByFromMemberId(1, PageRequest.of(0, 100));
        List<Block> list = pageResult.getContent();

        Block bbb = list.get(0);
        assertThat(bbb.getFromMember().getId()).isEqualTo(1);
        assertThat(bbb.getToMember().getId()).isEqualTo(3);

        Long deleteCount = service.deleteAllByFromMemberId(1);

        assertThat(deleteCount).isEqualTo(1);
    }
}