package team.foobar.service.syscode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Syscode;
import team.foobar.dto.SyscodeDto;
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
    public Syscode search(String code) {
        return repository.findById(code).orElse(null);
    }

    @Override
    public List<Syscode> searchAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Syscode create(SyscodeDto dto) {
        return repository.save(DtoToEntity(dto));
    }

    @Override
    @Transactional
    public Integer update(SyscodeDto dto) {
        Syscode parent = this.search(dto.getParentCode());
        if(parent == null) {
            return -1;
        }

        Syscode syscode = this.search(dto.getCode());
        syscode.change(dto.getCode(), Syscode.builder().code(dto.getParentCode()).build(), dto.getValue());
        return 1;
    }

    @Override
    @Transactional
    public void delete(String code) {
        repository.deleteById(code);
    }

    @Override
    public Page<Syscode> searchPage(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
