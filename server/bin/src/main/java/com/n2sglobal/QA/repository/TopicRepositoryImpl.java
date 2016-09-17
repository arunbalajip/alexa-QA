package com.n2sglobal.QA.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.n2sglobal.QA.topic.entity.Topic;
@Repository
@Transactional
public class TopicRepositoryImpl implements TopicRepository{
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	@Override
	public List<Topic> findAll() {
		TypedQuery<Topic> query = em.createNamedQuery("Topic.findAll", Topic.class);
		return query.getResultList();
	}

	@Override
	public Topic findOne(String id) {
		return em.find(Topic.class, id);
	}

	@Override
	public Topic findByTopic(String topic) {
		TypedQuery<Topic> query = em.createNamedQuery("Topic.findByTopic", Topic.class);
		query.setParameter("mtopic", topic);
		List<Topic> topics = query.getResultList();
		if (topics != null && topics.size() == 1) {
			return topics.get(0);
		}
		return null;
	}

	@Override
	public Topic create(Topic topic) {
		em.persist(topic);		
		return topic;
	}

	@Override
	public Topic update(String id, Topic topic) {
		return em.merge(topic);
	}

}
