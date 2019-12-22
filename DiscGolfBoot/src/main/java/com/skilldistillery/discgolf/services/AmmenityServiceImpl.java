package com.skilldistillery.discgolf.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.discgolf.entities.Address;
import com.skilldistillery.discgolf.entities.Ammenity;
import com.skilldistillery.discgolf.repositories.AmmenityRepository;

@Service
public class AmmenityServiceImpl implements AmmenityService {
	@Autowired
	AmmenityRepository repo;

	@Override
	public List<Ammenity> allAmmenities() {
		return repo.findAll();
	}

	@Override
	public Ammenity findById(int id) {
		Ammenity ammenity;
		Optional<Ammenity> op = repo.findById(id);
		if(op.isPresent()) {
			ammenity = op.get();
		}else {
			ammenity = null;
		}
		return ammenity;
	}

	@Override
	public Ammenity create(Ammenity ammenity) {
		if (ammenity != null) {
			repo.saveAndFlush(ammenity);
			return ammenity;
		}else {
			return null;
		}
	}

	@Override
	public Ammenity replace(int id, Ammenity ammenity) {
		Optional<Ammenity> op = repo.findById(id);
		if (op.isPresent()) {
			ammenity.setId(op.get().getId());
		
			repo.saveAndFlush(ammenity);
		}
		return ammenity;
	}

	@Override
	public Boolean deleteById(int id) {
		Optional<Ammenity> op = repo.findById(id);
		if (op.isPresent()) {
			repo.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

}
