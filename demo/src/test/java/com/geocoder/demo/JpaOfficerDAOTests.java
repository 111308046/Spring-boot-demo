package com.geocoder.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.geocoder.demo.dao.OfficerDAO;
import com.geocoder.demo.entities.Officer;
import com.geocoder.demo.entities.Rank;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JpaOfficerDAOTests {
	@Autowired @Qualifier("jpaOfficerDAO")
	OfficerDAO dao;
	

	@Test
	public void save() throws Exception {
	    Officer officer = new Officer(Rank.LIEUTENANT, "Nyota", "Uhuru");
	    officer = dao.save(officer);
	    assertNotNull(officer.getId());
	}
	
	@Test
	public void findByIdThatExists() throws Exception {
	    Optional<Officer> officer = dao.findById(1);
	    assertTrue(officer.isPresent());
	    assertEquals(1, officer.get().getId().intValue());
	}

	@Test
	public void findByIdThatDoesNotExist() throws Exception {
		
		int[] a = new int[10];
		
	    Optional<Officer> officer = dao.findById(999);
	    assertFalse(officer.isPresent());
	}
	
	@Test
	public void count() throws Exception {
	    assertEquals(5, dao.count());
	}
	
	@Test
	public void findAll() throws Exception {
	    List<String> dbNames = dao.findAll().stream()
	            .map(Officer::getLast)
	            .collect(Collectors.toList());
	    assertThat(dbNames, Matchers.containsInAnyOrder("Kirk", "Picard", "Sisko", "Janeway", "Archer"));
	}

	@Test
	public void delete() throws Exception {
	    IntStream.rangeClosed(1, 5)
	            .forEach(id -> {
	                Optional<Officer> officer = dao.findById(id);
	                assertTrue(officer.isPresent());
	                dao.delete(officer.get());
	            });
	    assertEquals(0, dao.count());
	}

	@Test
	public void existsById() throws Exception {
	    IntStream.rangeClosed(1, 5)
	            .forEach(id -> assertTrue(String.format("%d should exist", id), dao.existsById(id)));
	}
}
