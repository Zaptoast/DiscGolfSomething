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

public class CourseTests {


	private static EntityManagerFactory emf;
	private EntityManager em;
	private Course course;

	@BeforeAll
	public static void setUpAll() {
		emf = Persistence.createEntityManagerFactory("DiscGolfPU");
		

	}

	@BeforeEach
	public void setUp() throws Exception {
		em = emf.createEntityManager();
		course = em.find(Course.class, 1);
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
		course = null;
	}

	@AfterAll
	public static void closeAll() {
		emf.close();

	}
	
	@Test
	public void test_entity_mapping() {
		assertEquals("DG course 1", course.getName());
		assertEquals(1, course.getAddress().getId());
		assertEquals("Bathroom", course.getAmmenities().get(0).getName());
		assertEquals(18, course.getLength());
		
	}
}
