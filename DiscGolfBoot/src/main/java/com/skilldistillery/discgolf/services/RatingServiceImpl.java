package com.skilldistillery.discgolf.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.discgolf.entities.Rating;
import com.skilldistillery.discgolf.repositories.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {
	@Autowired
	RatingRepository repo;

	@Override
	public Rating create(Rating rating) {
		System.out.println(rating.getCourse());
		if (rating != null) {
			repo.save(rating);
			repo.flush();
			return rating;
		} else {
			return null;
		}
	}

}
