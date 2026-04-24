package com.algaworks.algacomment.comment.service.api.client.model.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModerationCommentOutput {
    private Boolean approved;
    private String reason;
}
