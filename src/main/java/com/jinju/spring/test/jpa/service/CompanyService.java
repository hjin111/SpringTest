package com.jinju.spring.test.jpa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinju.spring.test.jpa.domain.Company;
import com.jinju.spring.test.jpa.repository.CompanyRepository;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	public Company addCompany(String name, String business, String scale, int headcount) {
		// 이름, 사업분야, 규모, 사원 수를 전달 받아서 이에 대응되는 데이터들을 저장하는 과정이다.
		// repository 를 통해서 수행 시켜 줄거고 저장 과정은 이미 만들어진 메소드를 그대로 활용해주면 됨
		
		// 여기에 우리가 직접 메소드를 만들지는 않았지만 상속을 통해서 활용할 수 있도록 세팅된 메소드
		// 어떤거?? 한 행을 저장할 떈 save 이다. save 메소드에 전달될 인자 값은 저장할 값인데 저장할 값은 총 4가지이지만
		// 하나의 인자이다 머다?? Entity 클래스 객체로 필요한 값을 멤버 변수에 저장 해서 전달해주면 된다.
		// 그럼 여기서 Entity 클래스 객체를 하나 만들어서 전달된 위에 값들을 멤버 변수 값으로 채워 넣으면 된다.
		// 우리가 원래 알고 있던 객체를 생성하고 생성자를 통해서 저장한다거나 객체를 생성하고 setter 를 통해서 값을 채우는 방법동 있지만 새로운 것들 해보면 좋음
		// builder 패턴을 활용 하는게 맞음 builder 패턴을 통해 Entity 클래스를 생성하고 멤버 변수 값을 채워보도록 한다.
		
		// Company.builder() 뒤에 .으로 이어 붙여지는 키워드는 해당하는 클래스의 멤버 변수 이다.
		Company company = Company.builder()
							.name(name)
							.business(business) // 괄호 안에 들어갈게 파라미터로 전달 될 값이다.
							.scale(scale)
							.headcount(headcount)
							.build();
		// 생성자는 미리 몇개를 전달 받아서 처리하는 파라미터일지는 미리 정해놓은거고 정해놓은걸 사용해야 하지만
		// 여기서는 따로 정해 놓을 필요 없이 원하는 만큼만 추가해서 객체를 생성할 수 있다.
		// 그럼 얘가 멀 리턴해준다 ?? Company 객체
		
		company = companyRepository.save(company); // 이 save 메소드가 수행이 되면 실제 insert 가 진행이 될거고 insert 쿼리 작성 한적도 없지만 jpa 가 알아서 해줌
		                                 		   // 멀 리턴해준다?? insert 하고 실행된 결과 다시 채워진 객체를 리턴해준다. 
		
		// mybatis 기반에서는 전달 된 값이 수행된 행의 갯수밖에 없으니 그걸 리턴했던거고 지금은 저장한 결과까지 객체로 얻어올 수 있으니깐 company가 리턴되면 좋음
		return company;
	}
	
	// id를 전달받고, 해당하는 회사의 규모와 사원수를 수정하는 기능
	public Company updateCompany(int id, String scale, int headcount){
		
		// JPA를 적용 했기 떄문에 기본적인 update 는 repository 에 JpaRepository에 상속 받은 메소드를 통해서 별다른 조치 없이 바로 사용 가능
		// UPDATE 할려면 과정이 있음
		// 업데이트할 대상 행의 객체를 얻는다. - id를 기반으로 업데이트 할 대상을 얻어낸다. ( 조회를 기반으로 대상 행의 정보를 얻어낸다. )
		// 해당 객체의 값을 수정한다.
		// 해당 객체를 다시 저장한다.
		Optional<Company> optionalCompany = companyRepository.findById(id); // 우리가 만들진 않았지만 이미 포함되어 있다. findById 라는 메소드를 호출 해서 인자로 조회 대상의 primary key를 전달해주면 우리가 직접 select 쿼리 수행 하지 않아도 알아서 내부적으로 select 쿼리 만들어서 수행시켜주고 그 결과를 리턴해준다. 
		// mybatis 처럼 Entity 클래스의 객체로 멤버 변수에 대응되는 컬럼의 값을 저장한 채 전달이 되는데 그냥 전달하는건 아니고 Optional 이라고 하는 형태의 객체로 감싸져서 전달이 된다.
		
		Company company = optionalCompany.orElse(null); // orElse 메소드를 통해서 혹시나 null 인 경우에는 어떤 형태로 리턴할지를 지정해 주고 그걸 기반으로 실제 저장된 객체를 불러오면 된다.
		// 조회가 되면 해당하는 Entity 객체가 리턴 될꺼고 조회가 안되면 orElse 인자에 직접 지정한 값을 기반으로 리턴이 되는데 null로 지정을 했으니깐 null일 떄는 null로 리턴
		
		// null일수도 있는 놈이면 company 가 null이 아닐 때만 수정이 진행이 되어야 한다.
		if(company != null) {
			// 해당 객체의 값을 수정한다.( null이 아닐 떄만 수정 과정이 진행이 되어야 한다.)
			company = company.toBuilder().scale(scale).headcount(headcount).build();
			// 해당 객체를 다시 저장한다. - 이 객체를 repository를 통해 실제 테이블 데이터로 수정을 해야 한다.
			company = companyRepository.save(company); // 최초로 저장하는거는 이미 있는 데이터를 수정 하는거는 저장한다는 개념이기 때문에 save 메소드 활용하면 된다.
		}
		
//		업데이트 할 대상 객체를 얻기 위해 id 기반으로 select로 조회해 온거구  
//		Optional 객체로 리턴이 되서 null에 대한 처리를 한 이후로 
//		객체를 얻어내고 그 객체를 기반으로 null이 아닐 떄만 
//		해당하는 객체의 필요한 멤버 변수 값을 수정하고 
//		그 수정된 결과 객체를 repository에 save메소드로 전달해주면 
//		일치하는 primary key 를 가진 행이 있으니깐 새롭게 추가한게 아니라 
//		그 행의 값을 수정해준다. 그 결과를 또 객체 형태로 리턴 
		
		return company;
	}
	
	public void deleteCompany(int id) {
		// 삭제할 대상 행의 객체를 얻는다.
		// 객체를 기반으로 삭제한다.
		
		// companyRepository.deleteById(id); // 한방에 삭제됨
		// 아래 과정을 한번에 처리 해주는 메소드가 companyRepository에 있긴 있다.
		
		Optional<Company> optionalCompany = companyRepository.findById(id);
		Company company = optionalCompany.orElse(null);
		
		if(company != null) { // null이 아닐 떄만 삭제 기능이 수행이 되도록 해준다.
			companyRepository.delete(company);
		}
	}
}
