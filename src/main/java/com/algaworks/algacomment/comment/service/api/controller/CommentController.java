package com.algaworks.algacomment.comment.service.api.controller;

import com.algaworks.algacomment.comment.service.api.model.input.CommentInput;
import com.algaworks.algacomment.comment.service.api.model.output.CommentOutput;
import com.algaworks.algacomment.comment.service.domain.model.Comment;
import com.algaworks.algacomment.comment.service.domain.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentRepository commentRepository;

    @GetMapping
    public Page<CommentOutput> search(@PageableDefault Pageable pageable) {
        Page<Comment> comments = commentRepository.findAll(pageable);
        return comments.map(this::convertToModel);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentOutput findById(@PathVariable UUID id) {
        Comment comment = returnComment(id);

        return convertToModel(comment);
    }

    @PostMapping
    public CommentOutput create(@RequestBody CommentInput commentInput) {
        Comment comment = Comment.builder()
                .text(commentInput.getText())
                .author(commentInput.getAuthor())
                .createdAt(OffsetDateTime.now())
                .build();

        comment = commentRepository.saveAndFlush(comment);

        return convertToModel(comment);
    }

    private CommentOutput convertToModel(Comment comment) {
        return CommentOutput.builder()
                .id(comment.getId())
                .text(comment.getText())
                .author(comment.getAuthor())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    private Comment returnComment(UUID id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return comment;
    }
}
