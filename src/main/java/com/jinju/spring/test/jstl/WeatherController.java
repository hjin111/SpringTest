package com.jinju.spring.test.jstl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jinju.spring.test.jstl.domain.Weather;
import com.jinju.spring.test.jstl.service.WeatherService;

@Controller
@RequestMapping("/jstl/weather")
public class WeatherController {
	
	@Autowired
	private WeatherService weatherService;
	
	@GetMapping("/list")
	public String weatherList(Model model) {
		
		// Controller 에서 테이블을 직접 조회해서 하는 기능은 역할이 아님
		// Controller는 Service 한테 시켜야 함 뭘?
		// 날씨 리스트 얻어오기
		List<Weather> weatherHistory = weatherService.getWeatherHistory();
		
		model.addAttribute("weatherList", weatherHistory);
		// weatherList으로 weatherHistory 이 리스트를 저장 한거고 weatherList 이 이름으로 jsp 에서 weatherHistory 이 값을 쓸 수 있게 됨
		
		return "jstl/weather/list";
	}
	
	@GetMapping("/input")
	public String inputWeather() {
		return "jstl/weather/input";
	}
	
	@GetMapping("/insert")
	public String insertWeather(
			
			// 전달된 문자열을 date 객체로 변환 하기 위해서는 년,월,일 시,분,초의 규격을 전달해야 그걸 기반으로 년,월,일 시,분,초 데이터를 뜯어내서 date 객체로 만들어 낼 수 있으니깐
			// 그 규격을 알려주면 되는데 알려주기 위한 어노테이션인 @DateTimeFormat 이것을 하나 붙여주면 됨.
//			@DateTimeFormat(pattern="yyyy년 M월 d일") @RequestParam("date") Date date // 2024년 2월 17일 => 전달된 날짜 형태가 이 형태임.
//			, @RequestParam("weather") String weather
//			, @RequestParam("temperatures") double temperatures
//			, @RequestParam("precipitation") double precipitation
//			, @RequestParam("microDust") String microDust
//			, @RequestParam("windSpeed") double windSpeed
			@ModelAttribute Weather weather) { // @ModelAttribute 어노테이션을 추가해주면 이제 전달 될 파라미터 이름과 매칭되는 멤버 변수에다가 해당하는 파라미터 값을 넣어준다.
											   // 스프링 프레임워크가 알아서 객체 생성해서 전달될 파라미터 이름과 매칭하는 멤버변수의 값 채워넣어줄거임.
		
		// int count = weatherService.addWeather(date, weather, temperatures, precipitation, microDust, windSpeed);
		
		weatherService.addWeatherByObject(weather); // 이 object는 requestParameter를 직접 객체 생성 해서 일치하는 파라미터 이름에 멤버변수의 값을 저장시켜서 만든 객체이다.
													// 객체 생성 spring이 알아서 다 해주는거임.
		
		return "redirect:/jstl/weather/list";
		
//		return "수행 결과 : " + count;
		
	}
	
	// 하나하나 입력되는 파라미터 값들을 한번에 묶어서 전달 받을 수는 없을까?
	// 6개의 데이터를 하나로 묶어서 관리하는 데이터의 형태가 Entity 클래스
	// weather 클래스로 객체를 생성해서 거기에 있는 멤버변수에다가 파라미터로 전달된 값을 전달받으면 이 파라미터 값을 한방에 전달 받을 수 있음.
	// 이 파라미터랑 정확히 일치하는 멤버 변수를 가진 객체 아무래도 Entity 클래스이다.

}
