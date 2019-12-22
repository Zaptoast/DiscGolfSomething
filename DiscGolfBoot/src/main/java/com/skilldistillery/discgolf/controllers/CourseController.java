package com.skilldistillery.discgolf.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.discgolf.entities.Course;
import com.skilldistillery.discgolf.services.CourseService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4203"})
public class CourseController {
	@Autowired
	CourseService svc;
	
//	@GetMapping("ping")
//	public String pingPong() {
//		return "pong";
//	}
	
	@GetMapping("courses")
	public List<Course> getAllCourses(){
		return svc.allCourses();
	}
	@GetMapping("courses/{id}")
	public Course getCourseById(@PathVariable int id, HttpServletResponse resp) {
		Course course = svc.findById(id);
		if(course != null) {
			resp.setStatus(200);
			return course;
		}else {
			resp.setStatus(404);
			return null;
		}
	}
	@PostMapping("courses")
	public Course createCourse(@RequestBody Course course, HttpServletResponse resp, HttpServletRequest req) {
		System.out.println(course.getAddress());
		try{
			if(svc.create(course) != null) {
				resp.setStatus(201);
				StringBuffer sb = req.getRequestURL();
				sb.append("/");
				sb.append(course.getId());
				resp.addHeader("Location", sb.toString());
			}
		}catch(Exception e) {
			System.err.println(e);
			resp.setStatus(418);
		}
		return course;
		
	}
	@PutMapping("courses/{id}")
	public Course replacePost(@RequestBody Course course, @PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
	
			Course updateCourse = svc.replace(id, course);
			
		if(updateCourse == null) {
			resp.setStatus(404);
		}
		return updateCourse;
	}
	@DeleteMapping("courses/{id}")
	public Boolean deleteCourse(@PathVariable int id, HttpServletResponse resp) {
		Boolean success = svc.deleteById(id);
		if (success) {
			resp.setStatus(200);
		}else {
			resp.setStatus(418);
		}
		return success;
	}
//	keyword and zip are strings maybe conflict
	@GetMapping("courses/search/keyword/{keyword}")
	public List<Course> findByNameOrDescription(@PathVariable String keyword, HttpServletResponse resp){
		List<Course> courses = svc.findByNameOrDescription(keyword);
		if(courses != null) {
			resp.setStatus(200);
			return courses;
		}else {
			resp.setStatus(404);
			return null;
		}
	}
//	length and id are both ints may cause conflict
	@GetMapping("courses/search/{length}")
	public List<Course> findByLength(@PathVariable Integer length, HttpServletResponse resp){
		List<Course> courses = svc.findByLength(length);
		if(courses != null) {
			resp.setStatus(200);
			return courses;
		}else {
			resp.setStatus(404);
			return null;
		}
	}
	@GetMapping("courses/ammenities/{id}")
	public List<Course> findByAmmenityId(@PathVariable Integer id, HttpServletResponse resp){
		List<Course> courses = svc.findByAmmenity(id);
		if(courses != null) {
			resp.setStatus(200);
			return courses;
		}else {
			resp.setStatus(404);
			return null;
		}
	}
	@GetMapping("courses/addresses/{zip}")
	public List<Course> findByZip(@PathVariable String zip, HttpServletResponse resp){
		List<Course> courses = svc.findByZip(zip);
		if(courses != null) {
			resp.setStatus(200);
			return courses;
		}else {
			resp.setStatus(404);
			return null;
		}
	}

}
