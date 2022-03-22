package team.foobar.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import team.foobar.domain.Board;
import team.foobar.domain.CategoryBoard;
import team.foobar.domain.Syscode;
import team.foobar.dto.categoryboard.CategoryBoardDto;
import team.foobar.dto.syscode.SyscodeDto;
import team.foobar.service.board.BoardService;
import team.foobar.service.syscode.SyscodeService;
import team.foobar.service.member.MemberService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {
    private final SyscodeService syscodeService;
    private final MemberService memberService;
    private final BoardService boardService;

    @PostMapping
    public String update(@RequestBody SyscodeDto dto) {
        syscodeService.update(dto);
        return "complete";
    }

//    @GetMapping
//    public Object searchTest() {
//        List<Syscode> root = syscodeService.searchByParentCode("root");
//
//        List<SyscodeDto> collect = root.stream().map(el ->
//                SyscodeDto.builder()
//                        .code(el.getCode())
//                        .parentCode(el.getParentSys().getCode())
//                        .value(el.getValue())
//                        .build()
//        ).collect(Collectors.toList());
//
//        return collect;
//    }

//    @GetMapping
//    public Object all(4
//            @RequestParam(defaultValue = "1") Integer page,
//            @RequestParam(defaultValue = "10") Integer size) {
//        Page<Board> list = boardService.searchByCategoryId(1, PageRequest.of(page, size));
//        List<BBB> collect = list.stream().map(el -> new BBB(el)).collect(Collectors.toList());
//        return collect;
//    }
//
//    @Getter
//    static class BBB {
//        private Integer id;
//        private List<CategoryBoardDto> cbList;
//        public BBB(Board board) {
//            this.id = board.getId();
//            List<CategoryBoardDto> collect = board.getCbList().stream().map(el ->
//                    CategoryBoardDto.builder()
//                            .categoryId(el.getCategory().getId())
//                            .boardId(el.getBoard().getId())
//                            .categoryName(el.getCategory().getName())
//                            .build()
//            ).collect(Collectors.toList());
//
//            this.cbList = collect;
//        }
//    }
}