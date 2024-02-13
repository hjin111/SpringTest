package com.jinju.spring.test.mybatis.domain;

import java.util.Date;

// 한 행의 정보를 리턴 하기 위한 타입을 구성 하기 위해서 만듬
public class RealEstate {
	
	// 멤버 변수
	private int id;
	private int realtorId;
	private String address;
	private int area;
	private String type;
	private int price;
	private int rentPrice;
	private Date createdAt;
	private Date updatedAt;
	public int getId() {
		return id;
	}
	
	// getter 와 setter 는 규격이 정해져 있기 때문에 그 규격에 맞춰서 만들어 놓으면 다른 곳에서 쓸 때 규격대로 사용할 수 있기 때매 굳이 getter/setter 아닌 형태로 만들 필요가 없다. 
	public void setId(int id) {
		this.id = id;
	}
	public int getRealtorId() {
		return realtorId;
	}
	public void setRealtorId(int realtorId) {
		this.realtorId = realtorId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(int rentPrice) {
		this.rentPrice = rentPrice;
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
