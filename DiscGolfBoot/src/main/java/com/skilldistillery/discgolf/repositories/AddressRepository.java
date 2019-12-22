package com.skilldistillery.discgolf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.discgolf.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	

}
