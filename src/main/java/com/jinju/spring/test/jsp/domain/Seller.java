package com.jinju.spring.test.jsp.domain;

import java.util.Date;

public class Seller {
	
	// 멤버 변수
	private int id;
	private String nickname;
	private String profileImage;
	private double temperature;
	private Date createdAt;
	private Date updatedAt;
	
	// 이 Entity 클래스를 생성하고 값을 채우고 얻어오는 과정을 우리가 사용하는 mybatis 
	// EL 태그 등등 에서 활용헤야 하기 때문에 그 기능을 활용할 수 있도록 private인 멤버 변수를 접근할 수 있는 public getter/setter 를 만들어 주는 것이다.
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
