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
    public Optional<Banner> search(Integer id) {
        return repository.findByIdWithFetch(id);
    }

    @Override
    public Page<Banner> searchPage(Pageable pageable) {
        return repository.findAllWithFetch(pageable);
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

        Banner one = repository.findById(dto.getId()).get();
        one.change(Syscode.builder().code(dto.getSyscode()).build(), dto.getName(), dto.getStartDt(), dto.getEndDt(), dto.getUseFl(), dto.getOrd());
        return Optional.of(one.getId());
    }

    @Override
    @Transactional
    public void delete(Integer bannerId) {
        repository.deleteById(bannerId);
    }


    @Override
    public Integer searchLastOrd() {
        return repository.findLastOrd();
    }
}
