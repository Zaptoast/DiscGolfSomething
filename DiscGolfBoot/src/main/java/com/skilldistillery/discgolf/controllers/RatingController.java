package com.skilldistillery.discgolf.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.skilldistillery.discgolf.entities.Rating;
import com.skilldistillery.discgolf.services.RatingService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4203"})
public class RatingController {
	@Autowired
	RatingService svc;
	
	@PostMapping("ratings")
	public Rating createCourse(@RequestBody Rating rating, HttpServletResponse resp, HttpServletRequest req) {
		System.out.println(rating.getCourse());
		try{
			if(svc.create(rating) != null) {
				resp.setStatus(201);
				StringBuffer sb = req.getRequestURL();
				sb.append("/");
				sb.append(rating.getId());
				resp.addHeader("Location", sb.toString());
			}
		}catch(Exception e) {
			System.err.println(e);
			resp.setStatus(418);
		}
		return rating;
		
	}

}
