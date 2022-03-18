package team.foobar.service.syscode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Syscode;
import team.foobar.repository.jpa.syscode.SyscodeRepository;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Primary
public class SyscodeServiceImpl implements SyscodeService {
    private final SyscodeRepository repository;

    @Override
    public Syscode findOne(String code) {
        return repository.findById(code).orElse(null);
    }

    @Override
    public List<Syscode> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Syscode save(Syscode syscode) {
        return repository.save(syscode);
    }

    @Override
    @Transactional
    public void delete(String code) {
        repository.deleteById(code);
    }

    @Override
    public Page<Syscode> findAllPage(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
