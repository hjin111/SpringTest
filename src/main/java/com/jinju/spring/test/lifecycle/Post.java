package com.jinju.spring.test.lifecycle;

public class Post {
	
	// 멤버 변수 : 제목, 사용자, 내용
	private String title;
	private String user;
	private String content;
	
	// 해당 하는 멤버변수의 값을 어떻게 채울지를 고민해보자
	// 1. 생성자 - 객체가 생성될 때 채워지면 좋은 값들을 정리해주면 됨 2. getter / setter
	// 게시글이 만들어지면서 이 모든 값들이 채워지는게 자연스러울 것 같다
	// 객체 생성과 동시에 멤버 변수가 채워질 수 있도록 생성자 정리
	
	// 생성자 - 클래스 이름과 정확히 일치하는 특수한 기능을 가진 메소드 / 리턴 타입 없음
	public Post(String title, String user, String content) {
		
		this.title = title;
		this.user = user;
		this.content = content;
		
	}

	// 이 객체가 나중에 json 문자열로 만들어 져야 되고 우리가 직접 만드는게 아니라 Jackson 라이브러리라고 스프링 안에서 
	// 내부적으로 유기적으로 동작하는 라이브러리가 이 객체 내용을 끄집어내서 그 안에 있는 내용으로 문자열을 만들어 낼 겁니다
	// 근데 우리가 지금 만든 이 클래스는 멤버 변수가 모두 private 으로 되어 있다. 객체를 전달하더라도 이 객체 안에 있는 값을
	// 끄집어 낼 방법이 없단 얘기다. 그래서 getter / setter을 만들어서 쉽게 이 객체를 사용할 수 있도록 기능을 제공해준다
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	

}
