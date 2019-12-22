package com.skilldistillery.discgolf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.discgolf.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
