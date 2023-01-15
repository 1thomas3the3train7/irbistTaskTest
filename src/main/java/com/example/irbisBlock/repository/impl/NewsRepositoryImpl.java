package com.example.irbisBlock.repository.impl;

import com.example.irbisBlock.entity.News;
import com.example.irbisBlock.repository.NewsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NewsRepositoryImpl implements NewsRepository {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public void save(News news) {
        em.persist(news);
    }

    @Override
    public void delete(News news) {
        em.remove(em.contains(news) ? news : em.merge(news));
    }

    @Override
    public List<News> findAllNews(int page) {
        final List<News> news = em.createQuery("SELECT n FROM News n", News.class)
                .setFirstResult(5 * (page - 1))
                .setMaxResults(5)
                .getResultList();
        return news;
    }

    @Override
    public List<News> findNewsByTopicName(String topicName, int page) {
        if (page < 1) page = 1;
        final List<News> news = em.createQuery("SELECT n FROM News n " +
                "LEFT JOIN n.topic nt WHERE LOWER(nt.topicName) = ?1", News.class)
                .setParameter(1, topicName.toLowerCase())
                .setFirstResult(5 * (page - 1))
                .setMaxResults(5)
                .getResultList();
        return news;
    }

    @Override
    public List<News> findNewsBySourceName(String sourceName, int page) {
        if (page < 1) page = 1;
        final List<News> news = em.createQuery("SELECT n FROM News n LEFT JOIN n.topic nt " +
                "LEFT JOIN nt.source nts WHERE LOWER(nts.sourceName) = ?1", News.class)
                .setParameter(1,sourceName.toLowerCase())
                .setFirstResult(5 * (page - 1))
                .setMaxResults(5)
                .getResultList();
        System.out.println(news);
        return news;
    }
}
