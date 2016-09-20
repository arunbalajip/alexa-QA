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
import com.n2sglobal.QA.service.QuestionService;
import com.n2sglobal.QA.topic.entity.Question;
import com.n2sglobal.QA.topic.entity.Topic;

@RestController
@RequestMapping(path = "topic1")
public class QuestionController {

	@Autowired
	QuestionService quesService;

	@RequestMapping(method = RequestMethod.GET, path = "{id}/questions")
	public List<Question> findAll(@PathVariable("id") String topicId) {
		return quesService.findAll(topicId);
	}


	@RequestMapping(method = RequestMethod.POST, path = "{id}/questions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Question create(@PathVariable("id") String topicId, @RequestBody Question question) {
		return quesService.create(topicId, question);
	}
	

}
