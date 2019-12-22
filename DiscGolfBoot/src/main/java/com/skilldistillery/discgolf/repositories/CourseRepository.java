package com.skilldistillery.discgolf.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.discgolf.entities.Ammenity;
import com.skilldistillery.discgolf.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	List<Course> findByLength(Integer length);
	
	@Query("select c from Course c where name Like :keyword or description Like :keyword")
	List<Course> findByNameorDescription(@Param("keyword") String keyword);
	
	List<Course> findByAmmenities_Id(Integer id);
	
	List<Course> findByAddressCity(String city);
	
	List<Course> findByAddressZip(String zip);

}
