package com.n2sglobal.QA.service;

import java.util.List;

import com.n2sglobal.QA.topic.entity.Question;
import com.n2sglobal.QA.topic.entity.Topic;;

public interface QuestionService {
	public List<Question> findAll(String topicId);
	public Question findOne(String id);
	public Question findByQuestion(String question, Topic topic);
	public Question create(String id, Question question);
	public Question update(String id, Question question);

}
