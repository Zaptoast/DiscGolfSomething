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

import com.skilldistillery.discgolf.entities.Ammenity;
import com.skilldistillery.discgolf.services.AmmenityService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4203"})
public class AmmenityController {
	
	@Autowired
	AmmenityService svc;
	
	@GetMapping("ammenities")
	public List<Ammenity> getAllAmmenities(){
		return svc.allAmmenities();
	}
	@GetMapping("ammenities/{id}")
	public Ammenity getAmmenityById(@PathVariable int id, HttpServletResponse resp) {
		Ammenity ammenity = svc.findById(id);
		if(ammenity != null) {
			resp.setStatus(200);
			return ammenity;
		}else {
			resp.setStatus(404);
			return null;
		}
	}
	@PostMapping("ammenities")
	public Ammenity createAmmenity(@RequestBody Ammenity ammenity, HttpServletResponse resp, HttpServletRequest req) {
		try{
			if(svc.create(ammenity) != null) {
				resp.setStatus(201);
				StringBuffer sb = req.getRequestURL();
				sb.append("/");
				sb.append(ammenity.getId());
				resp.addHeader("Location", sb.toString());
			}
		}catch(Exception e) {
			System.err.println(e);
			resp.setStatus(418);
		}
		return ammenity;
		
	}
	@PutMapping("ammenities/{id}")
	public Ammenity replacePost(@RequestBody Ammenity ammenity, @PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
	
			Ammenity updateAmmenity = svc.replace(id, ammenity);
			
		if(updateAmmenity == null) {
			resp.setStatus(404);
		}
		return updateAmmenity;
	}
	@DeleteMapping("ammenities/{id}")
	public Boolean deleteAmmenity(@PathVariable int id, HttpServletResponse resp) {
		Boolean success = svc.deleteById(id);
		if (success) {
			resp.setStatus(200);
		}else {
			resp.setStatus(418);
		}
		return success;
	}

}
