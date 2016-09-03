package com.n2sglobal.QA.service;

import java.util.List;
import com.n2sglobal.QA.user.entity.User;

public interface UserService {
	public List<User> findAll();
	public User findOne(String id);
	public User findByUsername(String name);
	public User create(User user);
	public User update(String id, User user);
	
}