package com.example.application.data.service;

import com.example.application.data.entity.*;
import com.example.application.data.dto.TopicListItem;
import com.vaadin.flow.server.VaadinRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicService {
    private static final int MAX_VOTES = 5;
    private final TopicRepository topicRepository;
    private final VaadinerRepository vaadinerRepository;
    private final UpVoteRepository upVoteRepository;
    private final CommentRepository commentRepository;


    @Autowired
    public TopicService(TopicRepository topicRepository,
                        VaadinerRepository vaadinerRepository,
                        UpVoteRepository upVoteRepository,
                        CommentRepository commentRepository) {
        this.topicRepository = topicRepository;
        this.vaadinerRepository = vaadinerRepository;
        this.upVoteRepository = upVoteRepository;
        this.commentRepository = commentRepository;
    }

    public List<Topic> listAll() {
        return topicRepository.findAll();
    }

    public List<TopicListItem> searchTopics(Optional<String> searchTerm, Optional<Category> category) {
        return listAll().stream().filter(topic -> {
            if (searchTerm.isPresent()) {
                if (category.isPresent()) {
                    return (topic.getTitle().toLowerCase().contains(searchTerm.get().toLowerCase())
                            || topic.getDescription().toLowerCase().contains(searchTerm.get()))
                            && topic.getCategory().equals(category.get());
                } else {
                    return topic.getTitle().toLowerCase().contains(searchTerm.get().toLowerCase())
                            || topic.getDescription().toLowerCase().contains(searchTerm.get().toLowerCase());
                }
            } else if (category.isPresent()) {
                return category.get().equals(topic.getCategory());
            }
            return true;
        }).map(this::topicEntityToListItem).collect(Collectors.toList());
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
        topicListItem.setCommentCount(topic.getComments().size());
        topicListItem.setStatus(topic.getStatus());
        topicListItem.setCategory(topic.getCategory());
        topicListItem.setAnswerer(topic.getAnswerer());
        return topicListItem;
    }

    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    public void delete(Topic topic) {
        topicRepository.delete(topic);
    }

    public Topic submitNew(Topic topic, Vaadiner submitter) {
        // refresh the Vaadiner from the DB
        Vaadiner refreshedVaadiner = null;
        Optional<Vaadiner> optionalVaadiner = vaadinerRepository.findById(submitter.getId());
        if (optionalVaadiner.isEmpty()) {
            throw new EntityNotFoundException("Vaadiner not found");
        }

        topic.setStatus(Status.NEW);
        topic.setSubmitter(refreshedVaadiner);

        // save the topic
        Topic savedTopic = save(topic);

        // update Vaadiner
        refreshedVaadiner.getSubmittedTopics().add(savedTopic);
        vaadinerRepository.save(refreshedVaadiner);

        return savedTopic;
    }

    public Topic upvote(Topic topic, Vaadiner voter) {
        // create a new vote
        UpVote upVote = new UpVote();
        upVote.setTimestamp(LocalDate.now());
        upVote.setVoter(voter);

        // refresh the topic from the DB
        Optional<Topic> byId = topicRepository.findById(topic.getId());
        if (byId.isPresent()) {
            Topic topicToBeSaved = byId.get();
            topicToBeSaved.getUpVotes().add(upVote);

            // refresh the Vaadiner from the DB
            Vaadiner refreshedVaadiner = null;
            Optional<Vaadiner> optionalVaadiner = vaadinerRepository.findById(voter.getId());
            if (optionalVaadiner.isPresent()) {
                refreshedVaadiner = optionalVaadiner.get();
                if (refreshedVaadiner.getUpVotes().size() == MAX_VOTES) {
                    throw new IllegalArgumentException("All votes already used");
                }

            } else {
                throw new EntityNotFoundException("Vaadiner not found");
            }

            // save the vote
            upVote.setTopic(topicToBeSaved);
            UpVote savedVote = upVoteRepository.save(upVote);

            // update Vaadiner
            refreshedVaadiner.getUpVotes().add(savedVote);
            vaadinerRepository.save(refreshedVaadiner);

            // update the topic
            return save(topicToBeSaved);
        } else {
            throw new EntityNotFoundException("Topic not found");
        }
    }

    public Topic assign(TopicListItem topic, Vaadiner vaadiner) {
        // refresh the Vaadiner from the DB
        Optional<Vaadiner> optionalVaadiner = vaadinerRepository.findById(vaadiner.getId());
        if (optionalVaadiner.isEmpty()) {
            throw new EntityNotFoundException("Vaadiner not found");
        }

        // refresh the topic from the DB
        Optional<Topic> byId = topicRepository.findById(topic.getId());
        if (byId.isPresent()) {
            Topic topicToBeSaved = byId.get();
            topicToBeSaved.setStatus(Status.ASSIGNED);
            topicToBeSaved.setAnswerer(optionalVaadiner.get());

            // update the topic
            return save(topicToBeSaved);
        } else {
            throw new EntityNotFoundException("Topic not found");
        }
    }

    public Topic answered(TopicListItem topic) {
        // refresh the topic from the DB
        Optional<Topic> byId = topicRepository.findById(topic.getId());
        if (byId.isPresent()) {
            Topic topicToBeSaved = byId.get();
            topicToBeSaved.setStatus(Status.ANSWERED);

            // update the topic
            return save(topicToBeSaved);
        } else {
            throw new EntityNotFoundException("Topic not found");
        }
    }
    public Topic getTopicById(Long id) {
        return topicRepository.findById(id).orElse(null);
    }

    public UpVote saveUpVote(Long topicId) {
        Topic topic = getTopicById(topicId);
        UpVote upVote = new UpVote();
        upVote.setTopic(topic);
        upVote.setVoter(getCurrentVaadiner());
        return upVoteRepository.save(upVote);
    }

    public void removeUpVote(UpVote upVote) {
        upVoteRepository.delete(upVote);
    }

    public UpVote getUpVote(Long topicId) {
        Topic topic = getTopicById(topicId);

        UpVote upVote = new UpVote();
        upVote.setTopic(topic);
        upVote.setVoter(getCurrentVaadiner());
        return upVoteRepository.findOne(Example.of(upVote)).orElse(null);
    }

    //TODO move to correct Service
    public Vaadiner getCurrentVaadiner() {
        String extRefId = VaadinRequest.getCurrent().getUserPrincipal().getName();
        Vaadiner vaadiner = new Vaadiner();
        vaadiner.setExtReferenceId(extRefId);
        return vaadinerRepository.findOne(Example.of(vaadiner)).orElseThrow(() ->
                new IllegalStateException("No user found"));
    }

    public List<Comment> getCommentsForTopic(Long topicId) {
        return commentRepository.findAll((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("topic"), topicId));
    }

    public Long saveComment(Comment comment) {
        return commentRepository.save(comment).getId();
    }

}
