package com.n2sglobal.QA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n2sglobal.QA.exception.AlreadyExistsException;
import com.n2sglobal.QA.exception.NotFoundException;
import com.n2sglobal.QA.repository.UserRepository;
import com.n2sglobal.QA.user.entity.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository respository;

	@Override
	public List<User> findAll() {
		return respository.findAll();
	}

	@Override
	public User findOne(String id) {
		User existing = respository.findOne(id);
		if (existing == null) {
			throw new NotFoundException("User " + id + " not found");
		}
		return existing;
	}

	@Override
	public User findByUsername(String name) {
		User existing = respository.findByUsername(name);
		return existing;
	}

	@Override
	public User create(User user) {
		User existing = respository.findByUsername(user.getName());
		if (existing != null) {
			throw new AlreadyExistsException("Topic " + user.getName()
					+ " is already in use");
		}

		return respository.create(user);
	}

	@Override
	public User update(String id, User user) {
		User existing = respository.findOne(id);
		if (existing == null) {
			throw new NotFoundException("User " + id + " not found");
		}

		return respository.update(id, user);
	}

}
