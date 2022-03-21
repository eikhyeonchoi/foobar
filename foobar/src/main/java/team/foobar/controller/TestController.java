package team.foobar.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import team.foobar.dto.syscode.SyscodeDto;
import team.foobar.service.syscode.SyscodeService;
import team.foobar.service.member.MemberService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {
    private final SyscodeService syscodeService;
    private final MemberService memberService;

    @PostMapping
    public String update(@RequestBody SyscodeDto dto) {
        syscodeService.update(dto);
        return "complete";
    }

    @GetMapping
    public List<SyscodeDto> all() {
        return syscodeService.searchAll(0, 0).stream().map(el -> SyscodeDto.builder()
                .code(el.getCode())
                .parentCode(el.getParentSys() == null ? "" : el.getParentSys().getCode())
                .value(el.getValue())
                .createDt(el.getCreateDt())
                .updateDt(el.getUpdateDt())
                .build()).collect(Collectors.toList());
    }
}
