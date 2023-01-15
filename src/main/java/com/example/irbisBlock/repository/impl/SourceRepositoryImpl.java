package com.example.irbisBlock.repository.impl;

import com.example.irbisBlock.entity.News;
import com.example.irbisBlock.entity.Source;
import com.example.irbisBlock.entity.Topic;
import com.example.irbisBlock.repository.SourceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SourceRepositoryImpl implements SourceRepository {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public void save(Source source) {
        em.persist(source);
    }

    @Override
    public void delete(Source source) {
        em.remove(em.contains(source) ? source : em.merge(source));
    }

    @Override
    public List<Source> findAllSource(int page) {
        if (page < 1) page = 1;
        final List<Source> sources = em.createQuery("SELECT s FROM Source s", Source.class)
                .setFirstResult(5 * (page - 1))
                .setMaxResults(5)
                .getResultList();
        return sources;
    }

    @Override
    public List<Source> findAllSource() {
        final List<Source> sources = em.createQuery("SELECT s FROM Source s", Source.class)
                .getResultList();
        return sources;
    }

    @Override
    public Source findFullSourceBySourceName(String sourceName) {
        try {
            final Source source = em.createQuery("SELECT s FROM Source s " +
                            "LEFT JOIN FETCH s.topics st WHERE LOWER(s.sourceName) = ?1 ", Source.class)
                    .setParameter(1, sourceName.toLowerCase())
                    .getSingleResult();
            final List<Topic> topics = em.createQuery("SELECT t FROM Topic t LEFT JOIN FETCH t.news WHERE t in ?1", Topic.class)
                    .setParameter(1, source.getTopics())
                    .getResultList();
            source.setTopics(topics);
            return source;
        } catch (NoResultException n) {
            return null;
        }
    }
}
