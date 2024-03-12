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

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="recruit")
@Entity // 이 클래스가 Entity 클래스 라는걸 알려주는 어노테이션
public class Recruit {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="companyId")
	private int companyId;
	private Date deadline;
	private String position;
	private String responsibilities;
	private String qualification;
	private String type;
	private String region;
	private int salary;
	
	@UpdateTimestamp // 자동으로 현재시간이 저장도록 해주는 어노테이션
	@Column(name="createdAt", updatable=false)
	private Date createdAt;
	
	// 이 컬럼은 업데이트 될 떄마다 수정되는게 맞음
	@UpdateTimestamp // 자동으로 현재시간이 저장되도록 해주는 어노테이션
	@Column(name="updatedAt")// 카멜 케이스로 정의된 컬럼은 정확한 컬럼이름 매칭
	private Date updatedAt;

}
