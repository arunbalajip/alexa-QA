package com.n2sglobal.QA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.n2sglobal.QA.exception.AlreadyExistsException;
import com.n2sglobal.QA.exception.NotFoundException;
import com.n2sglobal.QA.repository.TopicRepository;
import com.n2sglobal.QA.topic.entity.Topic;

public class TopicServiceImpl implements TopicService {
	@Autowired
	TopicRepository respository;

	@Override
	public List<Topic> findAll() {
		return respository.findAll();
	}

	@Override
	public Topic findOne(String id) {
		Topic existing = respository.findOne(id);
		if (existing == null) {
			throw new NotFoundException("Topic " + id + " not found");
		}
		return existing;
	}

	@Override
	public Topic create(Topic topic) {
		Topic existing = respository.findByTopic(topic.getTopic());
		if (existing != null) {
			throw new AlreadyExistsException("Topic " + topic.getTopic()
					+ " is already in use");
		}

		return respository.create(topic);
	}

	@Override
	public Topic update(String id, Topic topic) {
		Topic existing = respository.findOne(id);
		if (existing == null) {
			throw new NotFoundException("Topic " + id + " not found");
		}

		return respository.update(id, topic);
	}

	@Override
	public Topic findByTopic(String topic) {
		Topic existing = respository.findByTopic(topic);
		if (existing != null) {
			throw new AlreadyExistsException("Topic " + topic
					+ " is already in use");
		}
		return existing;
	}

}
