package com.example.application.data.service;

import com.example.application.data.entity.Vaadiner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VaadinerRepository
        extends
            JpaRepository<Vaadiner, Long>,
            JpaSpecificationExecutor<Vaadiner> {

}
