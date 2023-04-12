package com.example.application.data.service;

import com.example.application.data.dto.TopicListItem;
import com.example.application.data.entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService {

    private TopicRepository topicRepository;

    public TopicService(@Autowired TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> listAll() {
        return topicRepository.findAll();
    }

    public List<TopicListItem> getAllTopicsSimplified() {
        var topics = topicRepository.findAll();
        return topics.stream().map(this::topicEntityToListItem).collect(Collectors.toList());
    }

    private TopicListItem topicEntityToListItem(Topic topic) {
        var topicListItem = new TopicListItem();
        topicListItem.setId(topic.getId());
        topicListItem.setUpvoteCount(topic.getUpVotes().size());
        topicListItem.setTitle(topic.getTitle());
        topicListItem.setDescription(topic.getDescription());
        return topicListItem;
    }

    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    public void delete(Topic topic) {
        topicRepository.delete(topic);
    }

}
