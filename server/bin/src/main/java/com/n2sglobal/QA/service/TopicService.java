package com.n2sglobal.QA.service;

import java.util.List;

import com.n2sglobal.QA.topic.entity.Topic;

public interface TopicService {
	public List<Topic> findAll();
	public Topic findOne(String id);
	public Topic findByTopic(String topic);
	public Topic create(Topic topic);
	public Topic update(String id, Topic topic);
	
}
