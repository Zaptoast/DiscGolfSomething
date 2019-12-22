package com.skilldistillery.discgolf.services;

import java.util.List;

import com.skilldistillery.discgolf.entities.Course;

public interface CourseService {
	List<Course> allCourses();
	Course findById(int id);
	Course create(Course course);
	Course replace(int id , Course course);
	Boolean deleteById(int id);
	List<Course> findByNameOrDescription(String keyword);
	List<Course> findByLength(Integer length);
	List<Course> findByAmmenity(Integer id);
	List<Course> findByZip(String zip);
}
