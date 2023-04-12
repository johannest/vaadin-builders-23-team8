package com.example.application.data.service;

import com.example.application.data.entity.Role;
import com.example.application.data.entity.Vaadiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaadinerService {

    private final VaadinerRepository vaadinerRepository;

    @Autowired
    public VaadinerService(VaadinerRepository vaadinerRepository) {
        this.vaadinerRepository = vaadinerRepository;
    }

    public List<Vaadiner> listAll() {
        return vaadinerRepository.findAll();
    }

    public List<Vaadiner> listAllLeaders() {
        return vaadinerRepository.findAll().stream().filter(vaadiner -> Role.HERD_LEADER.equals(vaadiner.getRole()))
                .collect(Collectors.toList());
    }
}
