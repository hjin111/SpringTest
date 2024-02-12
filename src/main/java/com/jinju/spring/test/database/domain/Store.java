package com.jinju.spring.test.database.domain;

import java.util.Date;

public class Store {
	
	// 이렇게 만들어진 entity형 클래스는 mybatis에 의해서 관리가 되고 사용되어질거다. 우리는 사용법에 맞춰서 클래스 구조를 잡아줌
	
	// 멤버 변수 - 컬럼 이름과 정확히 일치하는 변수 이름으로 만들어줘야 함
	private int id;
	private String name;
	private String phoneNumber;
	private String address;
	private String businessNumber;
	private String introduce;
	private Date createdAt;
	private Date updatedAt;
	
	// 우리가 직접 채우는 일은 거의 없고 mybatis에 의해 자동으로 객체 생성되고 멤버 변수 값까지 채워질거다.
	// 그걸 위해서 이 멤버 변수를 접근 하기 위한 방법을 제공해 줘야 된다.
	// 캡슐화 철학에 따라 멤버변수는 무조건 private으로 만들고 getter / setter 로 만들어 준다.
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
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
