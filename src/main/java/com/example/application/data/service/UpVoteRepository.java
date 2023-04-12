package com.example.application.data.service;

import com.example.application.data.entity.UpVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UpVoteRepository
        extends
            JpaRepository<UpVote, Long>,
            JpaSpecificationExecutor<UpVote> {

}
