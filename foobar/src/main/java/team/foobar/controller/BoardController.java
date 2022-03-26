package team.foobar.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.foobar.domain.Board;
import team.foobar.dto.Responser;
import team.foobar.dto.board.BoardDto;
import team.foobar.dto.board.BoardResponseDto;
import team.foobar.service.board.BoardService;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/board")
@SessionAttributes("user")
public class BoardController {
    private final BoardService boardService;
    private final MessageSource ms;

    @GetMapping
    public Responser<Object> getBoard(@RequestParam Integer page, @RequestParam Integer size) {
        Responser<Object> res = new Responser<>();
        Page<Board> list = boardService.searchAll(PageRequest.of(page, size));
        return res.setData(list.map(el -> new BoardResponseDto(el)));
    }

    @PostMapping
    private Responser<Object> addBoard(
            @SessionAttribute("memberId") Integer memberId,
            @Validated @RequestBody BoardDto dto,
            BindingResult br) throws Exception {
        Responser<Object> res = new Responser<>();
        if(br.hasFieldErrors()) {
            br.getFieldErrors().forEach(el -> res.setErrors(ms.getMessage(el.getCode(), el.getArguments(), null)));
            return res.setStatus(Responser.CLIENT_ERROR);
        }

        dto.setMemberId(memberId);
        Optional<Integer> createBoard = boardService.create(dto);
        if(createBoard.isEmpty()) {
            throw new Exception("server db error");
        }

        return res;
    }
}