package com.jinju.spring.test.ajax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinju.spring.test.ajax.domain.Favorite;
import com.jinju.spring.test.ajax.repository.FavoriteRepository;

@Service
public class FavoriteService {

	@Autowired // 해당 하는 변수에 객체를 생성해서 저장해야 하는데 그 과정을 스프링한테 맡기겠다.
	private FavoriteRepository favoriteRepository;
	
	public List<Favorite> getFavoriteList() {
		
		List<Favorite> favoriteList = favoriteRepository.selectFavoirteList();
		
		return favoriteList;
	}
	
	public int addFavorite(String name, String url) {
		int count = favoriteRepository.insertFavorite(name, url);
		return count;
	}
}
