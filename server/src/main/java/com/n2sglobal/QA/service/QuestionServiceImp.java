package com.n2sglobal.QA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n2sglobal.QA.exception.AlreadyExistsException;
import com.n2sglobal.QA.exception.NotFoundException;
import com.n2sglobal.QA.repository.QuestionRepository;
import com.n2sglobal.QA.topic.entity.Question;
import com.n2sglobal.QA.topic.entity.Topic;
@Service
public class QuestionServiceImp implements QuestionService {

	@Autowired
	QuestionRepository questionRepo;
	@Autowired
	TopicService Topicservice;
	@Override
	public List<Question> findAll(String topicId) {
		Topic topic = Topicservice.findOne(topicId);
		return questionRepo.findAll(topicId);
	}
	@Override
	public Question findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Question findByQuestion(String question, Topic topic) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Question create(String TopicId, Question question) {
	
		Topic topic = Topicservice.findOne(TopicId);
		
		question.setTopic(topic);
		
		return questionRepo.create(question);
		
	}
	@Override
	public Question update(String id, Question question) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
