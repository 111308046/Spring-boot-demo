package com.geocoder.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.geocoder.demo.entities.Officer;
import com.geocoder.demo.entities.Rank;

public interface OfficerRepository extends JpaRepository<Officer, Integer>{

	Optional<Officer> findById(@Param("id") int id);
	List<Officer> findByRank(@Param("rank") Rank rank);
	List<Officer> findByLast(@Param("last") String last);
}
