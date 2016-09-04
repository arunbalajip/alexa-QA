package com.n2sglobal.qa.dto;

import java.util.List;


public class User {

	private String id;
	private String name;
	private String email;
	private String alexa_userid;
	private List<Score> score;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAlexa_userid() {
		return alexa_userid;
	}
	public void setAlexa_userid(String alexa_userid) {
		this.alexa_userid = alexa_userid;
	}
	public List<Score> getScore() {
		return score;
	}
	public void setScore(List<Score> score) {
		this.score = score;
	} 
	
}
