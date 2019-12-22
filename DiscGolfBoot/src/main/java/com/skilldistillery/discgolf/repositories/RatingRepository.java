package com.skilldistillery.discgolf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.discgolf.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
