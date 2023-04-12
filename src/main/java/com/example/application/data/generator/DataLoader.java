package com.example.application.data.generator;

import com.example.application.data.entity.*;
import com.example.application.data.service.TopicRepository;
import com.example.application.data.service.UpVoteRepository;
import com.example.application.data.service.VaadinerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    private static final Logger LOG = LoggerFactory.getLogger(DataLoader.class);

    private final TopicRepository topicRepository;
    private final UpVoteRepository upVoteRepository;
    private final VaadinerRepository vaadinerRepository;

    public DataLoader(TopicRepository topicRepository, UpVoteRepository upVoteRepository,
                      VaadinerRepository vaadinerRepository) {
        this.topicRepository = topicRepository;
        this.upVoteRepository = upVoteRepository;
        this.vaadinerRepository = vaadinerRepository;

        createTestUsers();
        createTestData();

        LOG.info("Topics found {}", topicRepository.count());
        LOG.info("-------------------------------");
    }

    private void createTestUsers() {
        createVaadiner(100, "Steven Grandchamp", "steven@vaadin.com", Role.HERD_LEADER);
        createVaadiner(101, "Kimberly Weins", "kimw@vaadin.com", Role.HERD_LEADER);
        createVaadiner(102, "Jurka Rahikkala", "jurka@vaadin.com", Role.HERD_LEADER);
        createVaadiner(103, "Minna Hohti", "minna@vaadin.com", Role.HERD_LEADER);
    }

    private List<Topic> createTestData() {
        var topicList = new ArrayList<Topic>();
        topicList.add(createListItem(1, "What happened?", "Everything was according to plan, but now it isn't. What happened?", 25));
        topicList.add(createListItem(2, "Why would you do such a thing?", "Everyone told you not to, but you did. Why?", 1));
        topicList.add(createListItem(3, "Where is this going?", "I feel lost, I'm not sure where I am and where this ship is headed to. Can you help me understand?", 10));
        topicList.add(createListItem(4, "What now?", "What is the plan to resolve the problem of there existing problems?", 8));
        topicList.add(createListItem(5, "I am Too Short!!!", "Please help me grow.\n I want to be a big boi, but nothing seems to work.", 2));
        return topicList;
    }

    private Topic createListItem(long id, String title, String description, int upVoteCount) {
        var topic = new Topic();
        topic.setId(id);
        topic.setTitle(title);
        topic.setStatus(Status.NEW);
        topic.setDescription(description);

        List<UpVote> upVotes = new ArrayList<>();
        for (int i = 0; i < upVoteCount; i++) {
            UpVote upVote = new UpVote();
            upVote.setTimestamp(LocalDate.now());
//            upVote.setVoter(); TODO
            upVotes.add(upVote);
        }
        upVoteRepository.saveAll(upVotes);

        topic.setUpVotes(upVotes);


        topicRepository.save(topic);
        return topic;
    }

    private Vaadiner createVaadiner(long id, String name, String email, Role role) {
        Vaadiner vaadiner = new Vaadiner();
        vaadiner.setId(id);
        vaadiner.setName(name);
        vaadiner.setEmail(email);
        vaadiner.setRole(role);
        return vaadinerRepository.save(vaadiner);
    }
}
