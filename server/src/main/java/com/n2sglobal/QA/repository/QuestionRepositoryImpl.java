package com.n2sglobal.QA.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.n2sglobal.QA.topic.entity.Question;
import com.n2sglobal.QA.topic.entity.Topic;
@Repository
@Transactional
public class QuestionRepositoryImpl implements QuestionRepository{
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	@Override
	public List<Question> findAll(String topicId) {
		TypedQuery<Question> query = em.createNamedQuery("Question.findAll", Question.class);
		return query.getResultList();
	}

	@Override
	public Question findOne(String id, Topic topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question findByQuestion(String question, Topic topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question create(Question question) {
		em.persist(question);	
		return question;
	}

	@Override
	public Question update(String id, Question question) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
