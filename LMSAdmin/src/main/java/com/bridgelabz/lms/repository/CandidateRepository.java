package com.bridgelabz.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.lms.model.Candidate;


@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>{
	@Query(value = "select email from candidates where email=?1",nativeQuery = true)
	Optional<Candidate> isEmailExists(String email);

	
	@Query(value = "select * from candidates where id=?1",nativeQuery = true)
	Optional<Candidate> isIdExists(long id);
	
	@Query(value = "select * from candidates where id=?1",nativeQuery = true)
	Optional<Candidate> getUserById(long id);
	

}
