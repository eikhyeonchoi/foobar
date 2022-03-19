package team.foobar.service.banner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Banner;
import team.foobar.dto.BannerDto;
import team.foobar.repository.jpa.banner.BannerRepository;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Primary
public class BannerServiceImpl implements BannerService {

    private final BannerRepository repository;

    @Override
    public Banner search(Integer bannerId) {
        return repository.findById(bannerId).orElse(null);
    }

    @Override
    public List<Banner> searchPage() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Banner create(BannerDto dto) {
        return null;
    }

    public void update(BannerDto dto) {
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
        return repository.getLastOrd() + 1;
    }
}
