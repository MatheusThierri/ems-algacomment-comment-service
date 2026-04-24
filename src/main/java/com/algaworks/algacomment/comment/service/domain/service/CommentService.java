package com.algaworks.algacomment.comment.service.domain.service;

import com.algaworks.algacomment.comment.service.api.client.ModerationCommentClient;
import com.algaworks.algacomment.comment.service.api.client.model.input.ModerationCommentInput;
import com.algaworks.algacomment.comment.service.api.client.model.output.ModerationCommentOutput;
import com.algaworks.algacomment.comment.service.api.model.input.CommentInput;
import com.algaworks.algacomment.comment.service.api.model.output.CommentOutput;
import com.algaworks.algacomment.comment.service.domain.model.Comment;
import com.algaworks.algacomment.comment.service.domain.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ModerationCommentClient moderationCommentClient;

    public Page<CommentOutput> search(@PageableDefault Pageable pageable) {
        Page<Comment> comments = commentRepository.findAll(pageable);
        return comments.map(this::convertToModel);
    }

    public CommentOutput findById(UUID id) {
        Comment comment = returnComment(id);

        return convertToModel(comment);
    }

    public CommentOutput create(@RequestBody CommentInput commentInput) {
        Comment comment = Comment.builder()
                .text(commentInput.getText())
                .author(commentInput.getAuthor())
                .createdAt(OffsetDateTime.now())
                .build();

        ModerationCommentInput moderationCommentInput = ModerationCommentInput.builder()
                .text(comment.getText())
                .commentId(comment.getId())
                .build();

        ModerationCommentOutput moderationCommentOutput = moderationCommentClient.moderateComment(moderationCommentInput);
        if(Boolean.FALSE.equals(moderationCommentOutput.getApproved())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, moderationCommentOutput.getReason());
        }

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
        return commentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
