package team.foobar.service.banner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Banner;
import team.foobar.domain.Syscode;
import team.foobar.dto.banner.BannerDto;
import team.foobar.repository.jpa.banner.BannerRepository;
import team.foobar.repository.jpa.syscode.SyscodeRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Primary
public class BannerServiceImpl implements BannerService {

    private final BannerRepository repository;
    private final SyscodeRepository syscodeRepository;

    @Override
    public Banner search(Integer bannerId) {
        return repository.findById(bannerId).orElse(null);
    }

    @Override
    public List<Banner> searchAll() {
        return repository.findAllWithFetch();
    }

    @Override
    @Transactional
    public Optional<Integer> create(BannerDto dto) {
        Optional<Syscode> syscode = syscodeRepository.findById(dto.getSyscode());
        if(syscode.isEmpty()) {
            return Optional.empty();
        }

        Banner save = repository.save(dtoToEntity(dto));
        return Optional.of(save.getId());
    }

    @Override
    @Transactional
    public Optional<Integer> update(BannerDto dto) {
        Optional<Syscode> syscode = syscodeRepository.findById(dto.getSyscode());
        if(syscode.isEmpty()) {
            return Optional.empty();
        }

        Banner one = this.search(dto.getId());
        one.change(Syscode.builder().code(dto.getSyscode()).build(), dto.getName(), dto.getStartDt(), dto.getEndDt(), dto.getUseFl(), dto.getOrd());
        return Optional.of(one.getId());
    }

    @Override
    @Transactional
    public void delete(Integer bannerId) {
        repository.deleteById(bannerId);
    }

    @Override
    public Page<Banner> searchPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Integer findLastOrd() {
        return repository.getLastOrd();
    }
}
