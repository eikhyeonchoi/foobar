package team.foobar.service.syscode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import team.foobar.domain.Syscode;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class SyscodeServiceImplTest {

    @Autowired
    SyscodeService service;

    @Test
    void findOne() {
        Syscode root = service.findOne("root");

        assertThat(root.getParentSys()).isSameAs(null);
        assertThat(root.getValue()).isSameAs("루트");
    }

    @Test
    void findAll() {
        List<Syscode> all = service.findAll();

        assertThat(all.size()).isEqualTo(4);
    }

    @Test
    void save() {
        Syscode root = service.findOne("root");
        Syscode save = service.save(Syscode.createSyscode("test", root, "test"));
        Syscode findSys = service.findOne("test");
        assertThat(findSys).isSameAs(save);
        assertThat(service.findAll().size()).isEqualTo(5);
    }

    @Test
    void delete() {
        Syscode root = service.findOne("root");
        Syscode save = service.save(Syscode.createSyscode("test", root, "test"));

        service.delete("test");
    }

    @Test
    void findAllPage() {
        Page<Syscode> allPage = service.findAllPage(PageRequest.of(1, 1));
        System.out.println("allPage.getSize() = " + allPage.getSize());
        System.out.println("allPage.getNumberOfElements() = " + allPage.getNumberOfElements());
        for (Syscode syscode : allPage.getContent()) {
            System.out.println("syscode.getCode() = " + syscode.getCode());
        }

        System.out.println("allPage.isFirst() = " + allPage.isFirst());
        System.out.println("allPage.isLast() = " + allPage.isLast());
    }
}