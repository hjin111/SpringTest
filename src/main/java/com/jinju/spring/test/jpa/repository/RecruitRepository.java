package com.jinju.spring.test.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jinju.spring.test.jpa.domain.Recruit;

public interface RecruitRepository extends JpaRepository<Recruit, Integer>{

	// companyId 가 일치하는 행 조회
	// WHERE `companyId` = #{companyId}
	public List<Recruit> findByCompanyId(int companyId);
	
	// position 컬럼과 type 컬럼이 일치하는 행 조회 
	// WHERE `position` = #{position} AND `type` = #{type}
	public List<Recruit> findByPositionAndType(String position, String type);
	
	// type 컬럼이 일치하거나 salary 컬럼이 특정값 이상인 행 조회
	// WHERE `type` = #{type} OR `salary` >= #{salary}
	public List<Recruit> findByTypeOrSalaryGreaterThanEqual(String type, int salary);
	
	// type 컬럼 일치하고 연봉기준으로 내림차순 3개 조회
	// WHERE `type` = #{type} ORDER BY `salary` DESC LIMIT 3
	public List<Recruit> findTop3ByTypeOrderBySalaryDesc(String type);
	
  	// WHERE `region` = #{region} AND `salary` BETWEEN #{start} AND #{end}
	public List<Recruit> findByRegionAndSalaryBetween(String region, int startSalary, int endSalary);
	
	// deadline 컬럼이 특정값 보다 크고 salary가 특정값 보다 크고 type이 일치하는 데이터를 연봉 내림차순으로 조회
	@Query(value="SELECT * FROM `recruit` "
			+ " WHERE `deadline` > :deadline "
			+ " AND `salary` >= :salary "
			+ " AND `type` = :type "
			+ " ORDER BY `salary` DESC", nativeQuery=true)
	public List<Recruit>findByNativeQuery(
			@Param("deadline")String deadline
			, @Param("salary") int salary
			, @Param("type") String type);
}
