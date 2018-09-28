package com.geocoder.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.geocoder.demo.entities.Officer;
import com.geocoder.demo.entities.Rank;

@Repository
public class JdbcOfficerDAO implements OfficerDAO{

	JdbcTemplate jdbcTemplate;
	
	private SimpleJdbcInsert insertOfficer;
	
	@Autowired
	public JdbcOfficerDAO(DataSource dataSource){
		jdbcTemplate = new JdbcTemplate(dataSource);
		insertOfficer = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("officer")
                .usingGeneratedKeyColumns("id");
	}

	@Override
	public Officer save(Officer officer) {
		 Map<String,Object> parameters = new HashMap<>();
		    parameters.put("rank", officer.getRank());
		    parameters.put("first_name", officer.getFirst());
		    parameters.put("last_name", officer.getLast());
		    Integer newId = (Integer) insertOfficer.executeAndReturnKey(parameters);
		    officer.setId(newId);
		    return officer;
	}

	@Override
	public Optional<Officer> findById(Integer id) {
		if (!existsById(id)) return Optional.empty();
	    return Optional.of(jdbcTemplate.queryForObject(
	        "SELECT * FROM officer WHERE id=?",
	            new RowMapper<Officer>() {  // Java 7 anonymous inner class
	                @Override
	                public Officer mapRow(ResultSet rs, int rowNum) throws SQLException {
	                    return new Officer(rs.getInt("id"),
	                            Rank.valueOf(rs.getString("rank")),
	                            rs.getString("first_name"),
	                            rs.getString("last_name"));
	                }
	            },
	            id));
	}

	@Override
	public List<Officer> findAll() {
		 return jdbcTemplate.query("SELECT * FROM officer",
	            (rs, rowNum) -> new Officer(rs.getInt("id"), // Java 8 lambda expression
	                    Rank.valueOf(rs.getString("rank")),
	                    rs.getString("first_name"),
	                    rs.getString("last_name")));
	}

	@Override
	public long count() {
		return jdbcTemplate.queryForObject("select count(*) from officer", Long.class);
	}

	@Override
	public void delete(Officer officer) {
		jdbcTemplate.update("DELETE from officer where id=?", officer.getId());
		
	}

	@Override
	public boolean existsById(Integer id) {
		return jdbcTemplate.queryForObject(
		        "SELECT EXISTS(SELECT 1 FROM officer where id=?)", Boolean.class, id);
	}
}