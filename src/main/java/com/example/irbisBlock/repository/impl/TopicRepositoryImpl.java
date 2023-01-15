package com.example.irbisBlock.repository.impl;

import com.example.irbisBlock.entity.Topic;
import com.example.irbisBlock.repository.TopicRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TopicRepositoryImpl implements TopicRepository {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public void save(Topic topic) {
        em.persist(topic);
    }

    @Override
    public void delete(Topic topic) {
        em.remove(em.contains(topic) ? topic : em.merge(topic));
    }

    @Override
    public List<Topic> findFullAllTopic(int page) {
        try {
            if (page < 1) page = 1;
            final List<Topic> topics = em.createQuery("SELECT t FROM Topic t LEFT JOIN FETCH t.news", Topic.class)
                    .setFirstResult(5 * (page - 1))
                    .setMaxResults(5)
                    .getResultList();
            return topics;
        } catch (IllegalArgumentException a) {
            return new ArrayList<>();
        }
    }
}
