package com.n2sglobal.QA.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.n2sglobal.QA.service.TopicService;
import com.n2sglobal.QA.topic.entity.Topic;

@RestController
@RequestMapping(path = "topic")
public class TopicController {

	@Autowired
	TopicService service;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Topic> findAll() {

		return service.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, path = "{id}")
	public Topic findOne(@PathVariable("id") String id) {
		return service.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Topic findMoviesByTopic(@RequestParam(value="topic",required=true) String topic){
		return service.findByTopic(topic);
	}
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Topic create(@RequestBody Topic topic) {
		return service.create(topic);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Topic update(@PathVariable("id") String id, @RequestBody Topic topic) {
		return service.update(id, topic);
	}


}
