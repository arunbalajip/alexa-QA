package com.n2sglobal.QA.repository;

import java.util.List;

import com.n2sglobal.QA.topic.entity.Topic;
import com.n2sglobal.QA.user.entity.User;

public interface UserRepository {
	public List<User> findAll();
	public User findOne(String id);
	public User findByUsername(String user);
	public User create(User user);
	public User update(String id, User user);
}

