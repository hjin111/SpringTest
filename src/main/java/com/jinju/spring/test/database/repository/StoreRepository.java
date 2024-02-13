package com.jinju.spring.test.database.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jinju.spring.test.database.domain.Store;

@Mapper // xml과 매핑 되서 처리가 되는 인터페이스이다 라는걸 명시해주고 그와 관련된 기능을 수행 시켜주는 거임
public interface StoreRepository {

	// store 테이블의 모든 행을 조회하고 돌려준다. => 여기는 mybatis 라는 프레임 워크 기반으로 동작하기 때문에 사용법들이 많이 들어간다. ( 자바 문법과 프레임 워크 사용법이 버무러짐 )
	public List<Store> selectStoreList();
	// 우리가 이 메소드를 호출해서 select 쿼리를 수행하고자 하는거다
	// select 쿼리를 수행 하고 나면 당연히 조회 된 결과가 테이블로 만들어질거다
	// 이 결과를 자바의 객체 형태로 리턴을 해줘야 함 그러기 위해서 이 테이블에 있는 데이터를 어떤 형태로 객체 구조로 만들거냐를 정리해야 하는데 
	// mybatis 사용 법에 따라 여러 가지 방식이 있지만 가장 많이 쓰이는 방식은 한 행의 정보를 저장 할 객체를 구성 할 클래스를 설계해서 그 클래스를 기반으로 한 행씩 저장이 되고 그 객체들이 리스트를 통해서 구성이 되도록 정리를 하는 것이다.
	// 한 행의 정보를 저장하기 위한 클래스를 설계하면 됨 => 행에 들어있는 컬럼 값들을 저장할 수 있는 변수가 필요함, 컬럼 이름과 일치하는 멤버 변수를 가진 클래스를 설계해 주면 됨 그 클래스는 일반적으로 도메인 클래스, entity 클래스 라고 부름

	// 한 행의 정보를 방금 만든 entity 클래스로 구성할거고 여러 행이니깐 리스트를 통해 얻어올것이다 ( 리스트 안에 들어갈 한 행의 정보, 하나의 객체를 우리가 만든 entity 클래스로 만들거임 => List<Store>로 리턴 )
	// 인터페이스이기 때문에 구현 내용 없이 세미콜론으로 끝
	// 여기서 selectStoreList 호출 했을 때 수행될 기능을 만들어줘야 함
	// 원래 인터페이스로 정의 된 메소드는 클래스를 통해 implements 구현해야 된다
	// 그런데 지금은 mybatis 기반으로 쿼리를 동작시키기 위해서 만든 메소드이기 때문에 mybatis 사용 법에 따라 우리가 설정에 정의한 경로와 파일 규칙(mybatis.mapper-locations=mappers/*Mapper.xml)으로 xml 파일을 만들고 해당하는 이 인터페이스와 매핑해서 기능이 수행되도록 만들어주면 된다.
	// resources 아래 쪽에 mappers라는 패키지 만들고 그 안에 mapper.xml로 끝나는 xml 파일로 쿼리를 작성할 파일 만들기!!
	 
}
