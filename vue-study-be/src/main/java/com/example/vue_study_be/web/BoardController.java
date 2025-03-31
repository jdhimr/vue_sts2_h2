package com.example.vue_study_be.web;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.vue_study_be.entity.BoardEntity;
import com.example.vue_study_be.services.BoardService;
import com.example.vue_study_be.web.dtos.BoardDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/board/list")
    public List<BoardDto> boardList() { return boardService.getBoardList(); }

    @GetMapping("/board/{id}")
    public BoardDto geBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    @PostMapping("/board")
    public BoardEntity create(@RequestBody BoardDto boardDto) {
        return boardService.create(boardDto);
    }

    @PatchMapping("/board")
    public BoardEntity update(@RequestBody BoardDto boardDto) {
        return boardService.update(boardDto);
    }

    @DeleteMapping("/board/{id}")
    public void delete(@PathVariable Long id) {
        boardService.delete(id);
    }
}
