package com.skilldistillery.discgolf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.discgolf.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
