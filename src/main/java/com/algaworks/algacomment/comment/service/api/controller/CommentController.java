package com.algaworks.algacomment.comment.service.api.controller;

import com.algaworks.algacomment.comment.service.api.model.input.CommentInput;
import com.algaworks.algacomment.comment.service.api.model.output.CommentOutput;
import com.algaworks.algacomment.comment.service.domain.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public Page<CommentOutput> search(@PageableDefault Pageable pageable) {
        return commentService.search(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentOutput findById(@PathVariable UUID id) {
        return commentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentOutput create(@RequestBody CommentInput commentInput) {
        return commentService.create(commentInput);
    }
}
