package team.foobar.service.syscode;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.foobar.domain.Syscode;

import java.util.List;

public interface SyscodeService {
    Syscode findOne(String code);
    List<Syscode> findAll();
    Syscode save(Syscode syscode);
    void delete(String code);
    Page<Syscode> findAllPage(Pageable pageable);
}
