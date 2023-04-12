package com.example.application.data.service;

import com.example.application.data.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private TopicRepository topicRepository;

    public TopicService(@Autowired TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> listAll() {
        return topicRepository.findAll();
    }

    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    public void delete(Topic topic) {
        topicRepository.delete(topic);
    }

}
