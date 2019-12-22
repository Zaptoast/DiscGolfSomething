package com.skilldistillery.discgolf.services;

import java.util.List;

import com.skilldistillery.discgolf.entities.Ammenity;

public interface AmmenityService {
	
	List<Ammenity> allAmmenities();
	Ammenity findById(int id);
	Ammenity create(Ammenity ammenity);
	Ammenity replace(int id , Ammenity ammenity);
	Boolean deleteById(int id);

}
