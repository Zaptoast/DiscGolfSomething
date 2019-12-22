package com.skilldistillery.discgolf.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommentTest {


	private static EntityManagerFactory emf;
	private EntityManager em;
	private Comment comment;

	@BeforeAll
	public static void setUpAll() {
		emf = Persistence.createEntityManagerFactory("DiscGolfPU");
		

	}

	@BeforeEach
	public void setUp() throws Exception {
		em = emf.createEntityManager();
		comment = em.find(Comment.class, 1);
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
		comment = null;
	}

	@AfterAll
	public static void closeAll() {
		emf.close();

	}
	
	@Test
	public void test_entity_mapping() {
		assertEquals("This course rules", comment.getContent());
		assertEquals(1, comment.getCourse().getId());
		
	}
}
