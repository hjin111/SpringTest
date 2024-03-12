package com.jinju.spring.test.ajax.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jinju.spring.test.ajax.domain.Favorite;

@Mapper
public interface FavoriteRepository {
	
	public List<Favorite> selectFavoirteList();
	
	public int insertFavorite(
			@Param("name") String name
			, @Param("url") String url);
	
}
