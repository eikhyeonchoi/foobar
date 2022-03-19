package team.foobar.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import team.foobar.dto.SyscodeDto;
import team.foobar.service.syscode.SyscodeService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {
    private final SyscodeService syscodeService;

    @PostMapping
    public String update(@RequestBody SyscodeDto dto) {
        syscodeService.update(dto);
        return "complete";
    }
}
