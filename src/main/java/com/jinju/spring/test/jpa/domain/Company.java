package com.jinju.spring.test.jpa.domain;

import java.util.Date;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 처음 @Builder 라는 어노테이션 만으로는 수정까지가 안되고
// 여기에 추가로 toBuilder 라고 하는 속성을 true 로 달아줘야 수정 기능을 하는 toBuilder 까지 추가해준다. 
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="company") // 매칭되는 테이블 이름
@Entity
public class Company {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String business;
	private String scale;
	private int headcount;
	
	@UpdateTimestamp // 자동으로 현재시간이 저장도록 해주는 어노테이션
	@Column(name="createdAt", updatable=false)
	private Date createdAt;
	
	// 이 컬럼은 업데이트 될 떄마다 수정되는게 맞음
	@UpdateTimestamp // 자동으로 현재시간이 저장되도록 해주는 어노테이션
	@Column(name="updatedAt")// 카멜 케이스로 정의된 컬럼은 정확한 컬럼이름 매칭
	private Date updatedAt;

	
}
