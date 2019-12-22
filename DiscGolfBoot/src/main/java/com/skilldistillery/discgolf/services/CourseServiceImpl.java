package com.skilldistillery.discgolf.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.discgolf.entities.Ammenity;
import com.skilldistillery.discgolf.entities.Course;
import com.skilldistillery.discgolf.repositories.AmmenityRepository;
import com.skilldistillery.discgolf.repositories.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	CourseRepository repo;
	@Autowired
	AmmenityRepository amrepo;

	@Override
	public List<Course> allCourses() {
		return repo.findAll();
	}

	@Override
	public Course findById(int id) {
		Course course;
		Optional<Course> op = repo.findById(id);
		if (op.isPresent()) {
			course = op.get();
			return course;
		} else {
			return null;

		}
	}

	@Override
	public Course create(Course course) {
		if (course != null) {
			repo.save(course);
			repo.flush();
			return course;
		} else {
			return null;
		}
	}

	@Override
	public Course replace(int id, Course course) {
		Optional<Course> op = repo.findById(id);
		if (op.isPresent()) {
			course.setId(op.get().getId());
					
		}
		repo.saveAndFlush(course);
		return course;
	}

	@Override
	public Boolean deleteById(int id) {
		Optional<Course> op = repo.findById(id);
		if (op.isPresent()) {
			repo.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Course> findByNameOrDescription(String keyword) {
		List<Course> courses = repo.findByNameorDescription(keyword);
		if(courses != null) {
			if(courses.size()>0) {
				return courses;
			}
		}
		return null;
	}

	@Override
	public List<Course> findByLength(Integer length) {
		List<Course> courses = repo.findByLength(length);
		if(courses != null) {
			if(courses.size()>0) {
				return courses;
			}
		}
		return null;
	}

	@Override
	public List<Course> findByAmmenity(Integer id) {
		List<Course> courses;

			courses = repo.findByAmmenities_Id(id);
			return courses;

	}

	@Override
	public List<Course> findByZip(String zip) {
		List<Course> courses = repo.findByAddressZip(zip);
		if (courses != null) {
			if(courses.size()>0) {
				return courses;
			}else {
				return null;
			}
		}else {
			return courses;
		}
	}
	

}
