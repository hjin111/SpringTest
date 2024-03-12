package com.jinju.spring.test.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jinju.spring.test.jpa.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{
	
	// 기존에는 직접 메소드 만들고 쿼리 매칭시키는 과정이 있었지만 
	// 이번부터는 JpaRepository 라는걸 상속 받아서 여기에 제너릭을 Entity 클래스와 primary key 에 대응되는 타입을 잡아줌 
}
