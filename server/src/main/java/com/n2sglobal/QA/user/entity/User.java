package com.n2sglobal.QA.user.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
@NamedQueries({ 
	@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u ORDER BY u.name ASC"),
	@NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name=:uname") 
})
public class User {
	@Id
	@GenericGenerator(name = "customUUID", strategy = "uuid2")
	@GeneratedValue(generator = "customUUID")
	private String id;
	private String name;
	private String email;
	private String alexa_userid;
	@OneToMany(cascade = { CascadeType.ALL })
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
