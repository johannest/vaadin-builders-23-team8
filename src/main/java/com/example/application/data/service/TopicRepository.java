package com.example.application.data.service;

import com.example.application.data.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TopicRepository
        extends
            JpaRepository<Topic, Long>,
            JpaSpecificationExecutor<Topic> {

}
