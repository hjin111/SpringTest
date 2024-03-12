package com.jinju.spring.test.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinju.spring.test.jpa.domain.Recruit;
import com.jinju.spring.test.jpa.repository.RecruitRepository;

@Controller
@RequestMapping("/jpa/recruit")
public class RecruitController {

	@Autowired
	private RecruitRepository recruitRepository;
	
	@GetMapping("/read/1")
	@ResponseBody
	public Recruit selectRecruit() {
		
		// Optional 형태 객체구 null 일수도 있는 데이터를 리턴할 때 Optional 을 사용하는 경우가 있다.
		// null 일수도 있는 데이터를 Optional 객체로 전달받으면 이 optional 객체로부터 실제 얻고자 하는 데이터를 특정 메소드를 얻어 낼 수 있다.
		// 이 메소드는 null 처리를 어떻게 할건지에 대한 정리를 하는 메소드이다. 
		Optional<Recruit> optionalRecruit = recruitRepository.findById(8);
		
		// optional 객체는 거기서부터 null 처리 어떻게 할지를 기반으로 객체를 얻어와야 됨
		Recruit recruit = optionalRecruit.orElse(null);
		
		return recruit;
	}
	
	@GetMapping("/read/2")
	@ResponseBody
	public List<Recruit> readRecruitByCompanyId(@RequestParam("comapnyId")int companyId){
		List<Recruit> recruitList = recruitRepository.findByCompanyId(companyId);
		
		return recruitList;
	}
	
	@GetMapping("/read/3")
	@ResponseBody
	public List<Recruit> readRecruit(){
		// 웹 back-end 개발자 이고 정규직인 공고
		// List<Recruit> recruitList = recrutitReposiiton.findByPositionAndType("웹 back-end 개발자:, "정규직");
		// List<Recruit> recruitList = recruitRepository.findByTypeOrSalaryGreaterThanEqual("정규직", 9000);
		// List<Recruit> recruitList = recruitRepository.findTop3ByTypeOrderBySalaryDesc("계약직");
		// List<Recruit> recruitList = recruitRepository.findByRegionAndSalaryBetween("성남시 분당구", 7000, 8500);
		List<Recruit> recruitList = recruitRepository.findByNativeQuery("2016-04-10 00:00:00", 8100, "정규직");
		return recruitList;
		
	}
}
