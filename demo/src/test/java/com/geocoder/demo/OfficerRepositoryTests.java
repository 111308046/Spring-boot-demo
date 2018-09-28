package com.geocoder.demo;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.geocoder.demo.entities.Officer;
import com.geocoder.demo.entities.Rank;
import com.geocoder.demo.repositories.OfficerRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
@Transactional
public class OfficerRepositoryTests {
	@Autowired
	private OfficerRepository repository;
	
	@Autowired
	private JdbcTemplate template;
	
	@Test
	public void testSave(){
		Officer officer = new Officer(Rank.LIEUTENANT, "Nyota", "Uhuru");
		officer = repository.save(officer);
		assertNotNull(officer.getId());
	}
	
	@Test
	public void findById(){
		template.query("select id from officer", (rs, num) -> rs.getInt("id"))
			.forEach(id -> {
				Optional<Officer> officer = repository.findById(id);
				assertTrue(officer.isPresent());
				assertEquals(id, officer.get().getId());
			});
	}
	
	@Test
	public void findAll(){
		List<String> dbNames = repository.findAll().stream()
				.map(Officer::getLast)
				.collect(Collectors.toList());
		assertThat(dbNames, Matchers.containsInAnyOrder("Kirk", "Picard", "Sisko", "Janeway", "Archer"));
	}
	
	@Test
	public void count(){
		assertEquals(5, repository.count());
	}
	
	
	
}
