package com.example.application.data.generator;

import com.example.application.data.entity.Topic;
import com.example.application.data.entity.UpVote;
import com.example.application.data.service.TopicRepository;
import com.example.application.data.service.UpVoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    private static final Logger LOG = LoggerFactory.getLogger(DataLoader.class);

    private final TopicRepository topicRepository;
    private final UpVoteRepository upVoteRepository;

    public DataLoader(TopicRepository topicRepository, UpVoteRepository upVoteRepository) {
        this.topicRepository = topicRepository;
        this.upVoteRepository = upVoteRepository;

        createTestData();

        LOG.info("Topics found {}", topicRepository.count());
        LOG.info("-------------------------------");
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
}
