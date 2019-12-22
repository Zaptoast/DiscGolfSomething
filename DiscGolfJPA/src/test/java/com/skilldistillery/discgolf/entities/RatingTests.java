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

public class RatingTests {


	private static EntityManagerFactory emf;
	private EntityManager em;
	private Rating rating;

	@BeforeAll
	public static void setUpAll() {
		emf = Persistence.createEntityManagerFactory("DiscGolfPU");
		

	}

	@BeforeEach
	public void setUp() throws Exception {
		em = emf.createEntityManager();
		rating = em.find(Rating.class, 1);
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
		rating = null;
	}

	@AfterAll
	public static void closeAll() {
		emf.close();

	}
	
	@Test
	public void test_entity_mapping() {
		assertEquals(9.5, rating.getValue());
		assertEquals(1, rating.getCourse().getId());

		
	}

}
