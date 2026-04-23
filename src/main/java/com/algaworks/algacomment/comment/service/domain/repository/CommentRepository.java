package com.algaworks.algacomment.comment.service.domain.repository;

import com.algaworks.algacomment.comment.service.domain.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
