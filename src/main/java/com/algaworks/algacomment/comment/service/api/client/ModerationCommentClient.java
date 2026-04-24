package com.algaworks.algacomment.comment.service.api.client;

import com.algaworks.algacomment.comment.service.api.client.model.input.ModerationCommentInput;
import com.algaworks.algacomment.comment.service.api.client.model.output.ModerationCommentOutput;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/api/moderate")
public interface ModerationCommentClient {
    @PostExchange
    ModerationCommentOutput moderateComment(@RequestBody ModerationCommentInput moderationCommentInput);
}
