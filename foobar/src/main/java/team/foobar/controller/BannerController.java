package team.foobar.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.foobar.domain.Banner;
import team.foobar.dto.Responser;
import team.foobar.dto.banner.BannerDto;
import team.foobar.dto.banner.BannerResponseDto;
import team.foobar.service.banner.BannerService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/banner")
public class BannerController {
    private final BannerService bannerService;
    private final MessageSource ms;

    @GetMapping
    public Responser<Object> get(@RequestParam Integer page, @RequestParam Integer size) {
        Responser<Object> res = new Responser<>();

        Page<Banner> banners = bannerService.searchPage(PageRequest.of(page, size));
        banners.map(el -> new BannerResponseDto(el));
        return res.setData(banners);
    }


    @PostMapping
    public Responser<Object> addBanner(@Validated @RequestBody BannerDto dto, BindingResult br) throws Exception {
        Responser<Object> res = new Responser<>();

        log.info("hasError = {}", br.hasFieldErrors());
        if(br.hasFieldErrors()) {
            List<FieldError> fieldErrors = br.getFieldErrors();
            fieldErrors.stream().forEach(el -> res.setErrors(ms.getMessage(el.getCode(), el.getArguments(), null)));
            return res;
        }

        Optional<Integer> createBanner = bannerService.create(dto);
        if(createBanner.isEmpty()) {
            throw new Exception("server db error");
        }

        return res;
    }

    @PatchMapping("/{id}")
    public Responser<Object> updateBanner(@PathVariable Integer id, @RequestBody BannerDto dto, BindingResult br) throws Exception {
        Responser<Object> res = new Responser<>();
        dto.setId(id);

        if(br.hasFieldErrors()) {
            List<FieldError> fieldErrors = br.getFieldErrors();
            fieldErrors.stream().forEach(el -> res.setErrors(ms.getMessage(el.getCode(), el.getArguments(), null)));
            return res;
        }

        Optional<Integer> update = bannerService.update(dto);
        if(update.isEmpty()) {
            throw new Exception("server db error");
        }

        return res;
    }

    @DeleteMapping("/{id}")
    public Responser<Object> deleteBanner(@PathVariable Integer id) throws Exception {
        Responser<Object> res = new Responser<>();
        bannerService.delete(id);
        return res;
    }
}
