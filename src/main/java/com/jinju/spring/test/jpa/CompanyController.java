package com.jinju.spring.test.jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinju.spring.test.jpa.domain.Company;
import com.jinju.spring.test.jpa.service.CompanyService;

@Controller
@RequestMapping("/jpa/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	@GetMapping("/create")
	@ResponseBody
	public List<Company> createCompany() {
		
		List<Company> companyList = new ArrayList<>(); // 리스트를 만드는데 저장 할 객체의 형태가 Company 객체
		
//		회사명 : 넥손
//		사업내용 : 컨텐츠 게임
//		규모 : 대기업
//		사원수 : 3585명
		Company company = companyService.addCompany("넥손", "컨텐츠 게임", "대기업", 3585);
		// 뭘 리턴된다?? 저장된 결과로 만들어진 한 행의 정보가 모두 다 채워진 객체가 리턴된다.
		companyList.add(company); // 여기서 리턴 되서 저장해 놓은 company 객체를 add 메소드를 통해 리스트에 추가
		
//		회사명 : 버블팡
//		사업내용 : 여신 금융업
//		규모 : 대기업
//		사원수 : 6834명
		
		company = companyService.addCompany("버블팡", "여신 금융업", "대기업", 6834);
		companyList.add(company);
		// addCompany 메소드를 호출해서 기능 수행을 하고 나면 그 결과로 그 저장된 한 행의 정보를 저장할 Entity 클래스가 리턴된다.
		// 결국 company를 List로 감싸서 이걸 JSON 문자열로 만들 수 있도록 리턴해주면 됨.
		// company 객체 두 개를 저장한 List 를 만들어서 리턴된 객체를 리스트에 추가 하고 리스트를 response body 기반으로 리턴해주면 됨
		
		return companyList;
	}
	
	@GetMapping("/update")
	@ResponseBody
	public Company updateCompany() {
		
		// id가 8인 회사 정보 중 규모를 중소기업, 사원수를 34명 으로 수정하세요.
		Company company = companyService.updateCompany(8, "중소기업", 34);
		
		// company 객체를 response body에 JSON 문자 형태로 담으면 될거 같고 그거는 ResponseBody 어노테이션 기반으로 해당 객체를 그냥 리턴해주면 되겠다
		return company;
	}
	
	@GetMapping("/delete")
	@ResponseBody
	public String deleteCompany() {
		// id가 8인 회사 정보 제거
		companyService.deleteCompany(8);
		
		return "수행 완료";
	}
}
