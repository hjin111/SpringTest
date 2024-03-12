package com.jinju.spring.test.jstl.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinju.spring.test.jstl.domain.Weather;
import com.jinju.spring.test.jstl.repository.WeatherHistoryRepository;

@Service
public class WeatherService {
	
	// repository 객체가 필요한데 repository 는 멤버 변수 형태로 변수 하나 만들어 주기
	@Autowired
	private WeatherHistoryRepository weatherHistoryRepository;
	
	public List<Weather> getWeatherHistory() {
		// 날씨 정보는 weatherhistory 라고 하는 테이블에 저장 되어 있다.
		// select 쿼리를 통해서 테이블을 조회해야 됨 => service가 할 일이 아님
		// repository에 weatherhistory 테이블에 있는 모든 행의 정보를 조회해 오는 기능을 여기서 호출해서 수행 시키기
		
		List<Weather> weatherHistory = weatherHistoryRepository.selectWeatherHistory();
		
		return weatherHistory;
		
		// 이거 왜 만듬?
		// Controller 에서 jsp에 사용 될 데이터를 model 객체에 채워야 하는데 이 데이터를 Controller가 직접 얻어올 방법이 없다.
		// 그래서 Service를 통해 얻어오도록 하기 위해서 Service 에다가 날씨 정보를 얻어 오는 메소드를 만든 것이다.
		
		
		
	}
	
	public int addWeather(
				Date date
				, String weather
				, double temperatures
				, double precipitation
				, String microDust
				, double windSpeed) {
		
		int count = weatherHistoryRepository.insertWeather(date, weather, temperatures, precipitation, microDust, windSpeed);
	
		return count;
	}
	
	public int addWeatherByObject(Weather weather){
		
		int count = weatherHistoryRepository.insertWeatherByObject(weather);
		
		return count;
	}
	
	
	
}
