package com.jinju.spring.test.jstl.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jinju.spring.test.jstl.domain.Weather;

@Mapper
public interface WeatherHistoryRepository {
	
	// 여러 행이니깐 리스트가 될 거고 리스트에는 한 행을 저장 하기 위한 객체의 클래스 타입이 들어가야 된다.
	// 한 행의 정보는 해당 하는 테이블의 컬럼 이름과 정확히 일치하는 멤버 변수를 가진 entity 클래스 형태의 객체로 만들어 주면 된다.
	
	// 조회 결과는 여러 행이 될거니깐 List, 거기에 저장될 값은 Entity 클래스를 통해 한 행, 한 행 정보를 저장 하겠다.
	public List<Weather> selectWeatherHistory();
	
	public int insertWeather(
					@Param("date") Date date
					, @Param("weather") String weather
					, @Param("temperatures") double temperatures
					, @Param("precipitation") double precipitation
					, @Param("microDust") String microDust
					, @Param("windSpeed") double windSpeed);
	
	public int insertWeatherByObject(Weather weather);
	
}
