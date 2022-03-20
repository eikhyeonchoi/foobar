package team.foobar.service.syscode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Syscode;
import team.foobar.dto.syscode.SyscodeDto;
import team.foobar.repository.jpa.syscode.SyscodeRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Primary
public class SyscodeServiceImpl implements SyscodeService {
    private final SyscodeRepository repository;

    @Override
    public Optional<Syscode> search(String code) {
        return repository.findByIdWithFetch(code);
    }

    @Override
    public Optional<Syscode> searchNoFetch(String code) {
        return repository.findById(code);
    }

    @Override
    public List<Syscode> searchAll() {
        return repository.findAllWithFetch();
    }

    @Override
    @Transactional
    public Optional<String> create(SyscodeDto dto) {
        Optional<Syscode> search = this.search(dto.getParentCode());
        if(search.isEmpty()) {
            return Optional.empty();
        }

        Syscode save = repository.save(dtoToEntity(dto));
        return Optional.of(save.getCode());
    }

    @Override
    @Transactional
    public Optional<String> update(SyscodeDto dto) {
        Optional<Syscode> parent = this.search(dto.getParentCode());
        if(parent.isEmpty()) {
            return Optional.empty();
        }

        Optional<Syscode> searchOne = this.search(dto.getCode());
        if(searchOne.isEmpty()) {
            return Optional.empty();
        }

        Syscode one = searchOne.get();
        one.change(dto.getCode(), Syscode.builder().code(dto.getParentCode()).build(), dto.getValue());
        return Optional.of(one.getCode());
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
