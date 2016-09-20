package com.n2sglobal.QA.repository;

import java.util.List;

import com.n2sglobal.QA.topic.entity.Question;
import com.n2sglobal.QA.topic.entity.Topic;

public interface QuestionRepository {
	public List<Question> findAll(String topicId);
	public Question findOne(String id, Topic topic);
	public Question findByQuestion(String question, Topic topic);
	public Question create(Question question);
	public Question update(String id, Question question);
}
