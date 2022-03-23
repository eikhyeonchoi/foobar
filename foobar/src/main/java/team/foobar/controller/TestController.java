package team.foobar.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.foobar.dto.comment.CommentDto;
import team.foobar.dto.file.FileDto;
import team.foobar.dto.syscode.SyscodeDto;
import team.foobar.service.board.BoardService;
import team.foobar.service.comment.CommentService;
import team.foobar.service.file.FileService;
import team.foobar.service.syscode.SyscodeService;
import team.foobar.service.member.MemberService;
import team.foobar.util.FileManager;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {
    private final SyscodeService syscodeService;
    private final MemberService memberService;
    private final BoardService boardService;
    private final CommentService commentService;

    private final FileService fileService;
    private final FileManager fileManager;

    @GetMapping
    public String test() {
        return "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    }

    @PostMapping("/comment")
    public String commentCreate(@RequestBody CommentDto dto) {
        Optional<Integer> create = commentService.create(dto);
        System.out.println("create = " + create);
        return "complete";
    }

    @PostMapping("/syscode")
    public String syscodeCreate(@RequestBody SyscodeDto dto) {
        Optional<String> create = syscodeService.create(dto);
        System.out.println("create = " + create);
        return "complete";
    }

    @PostMapping("/file")
    public String fileCreate(MultipartFile file) throws JsonProcessingException {
        System.out.println("file.getOriginalFilename() = " + file.getOriginalFilename());
        String storeContext = fileManager.storeFile(file);


        Optional<Integer> board = fileService.create(FileDto.builder().tTable("board").tId(1).context(storeContext).build());
        return board.get().toString();
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