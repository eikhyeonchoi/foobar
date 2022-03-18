package team.foobar.service.banner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.foobar.domain.Banner;
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
    public Banner findOne(Integer bannerId) {
        return repository.findById(bannerId).orElse(null);
    }

    @Override
    public List<Banner> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Banner save(Banner banner) {
        return repository.save(banner);
    }

    @Override
    @Transactional
    public void delete(Integer bannerId) {
        repository.deleteById(bannerId);
    }

    @Override
    public Page<Banner> findAllPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Integer getLastOrd() {
        return repository.getLastOrd() + 1;
    }
}
