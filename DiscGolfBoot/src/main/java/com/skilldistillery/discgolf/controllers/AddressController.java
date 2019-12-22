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

import com.skilldistillery.discgolf.entities.Address;
import com.skilldistillery.discgolf.entities.Course;
import com.skilldistillery.discgolf.services.AddressService;
import com.skilldistillery.discgolf.services.CourseService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4203"})
public class AddressController {
	@Autowired
	AddressService svc;
	
	@Autowired
	CourseService csvc;
	
	
	@GetMapping("addresses")
	public List<Address> getAllAddresses(){
		return svc.allAddresses();
	}
	@GetMapping("addresses/{id}")
	public Address getAddressById(@PathVariable int id, HttpServletResponse resp) {
		Address address = svc.findById(id);
		if(address != null) {
			resp.setStatus(200);
			return address;
		}else {
			resp.setStatus(404);
			return null;
		}
	}
	@PostMapping("addresses")
	public Address createAddress(@RequestBody Address address, HttpServletResponse resp, HttpServletRequest req) {
		try{
			if(svc.create(address) != null) {
				resp.setStatus(201);
				StringBuffer sb = req.getRequestURL();
				sb.append("/");
				sb.append(address.getId());
				resp.addHeader("Location", sb.toString());
			}
		}catch(Exception e) {
			System.err.println(e);
			resp.setStatus(418);
		}
		return address;
		
	}
	@PutMapping("addresses/{id}")
	public Address replacePost(@RequestBody Address address, @PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
	
			Address updateAddress = svc.replace(id, address);
			
		if(updateAddress == null) {
			resp.setStatus(404);
		}
		return updateAddress;
	}
	@DeleteMapping("addresses/{id}")
	public Boolean deleteAddress(@PathVariable int id, HttpServletResponse resp) {
		Boolean success = svc.deleteById(id);
		if (success) {
			resp.setStatus(200);
		}else {
			resp.setStatus(418);
		}
		return success;
	}

}
