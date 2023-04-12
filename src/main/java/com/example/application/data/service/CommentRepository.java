package com.example.application.data.service;

import com.example.application.data.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommentRepository
        extends
            JpaRepository<Comment, Long>,
            JpaSpecificationExecutor<Comment> {

}
