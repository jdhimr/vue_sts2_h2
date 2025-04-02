package com.example.vue_study_be.web;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.vue_study_be.entity.BoardEntity;
import com.example.vue_study_be.model.Header;
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
    public Header<List<BoardDto>> boardList(
            @PageableDefault(sort = {"idx"}) Pageable pageable
    ) {
        return boardService.getBoardList(pageable);
    }

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
