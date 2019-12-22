package com.skilldistillery.discgolf.services;

import java.util.List;

import com.skilldistillery.discgolf.entities.Address;

public interface AddressService {
	
	List<Address> allAddresses();
	Address findById(int id);
	Address create(Address address);
	Address replace(int id , Address address);
	Boolean deleteById(int id);

}
