package com.algaworks.algacomment.comment.service.api.model.input;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentInput {
    private String text;
    private String author;
}
