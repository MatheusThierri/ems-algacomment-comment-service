package com.algaworks.algacomment.comment.service.api.client.model.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModerationCommentInput {
    private String text;
    private UUID commentId;
}
