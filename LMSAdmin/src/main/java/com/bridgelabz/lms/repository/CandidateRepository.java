package com.bridgelabz.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.lms.model.HiringCandidate;


@Repository
public interface CandidateRepository extends JpaRepository<HiringCandidate, Long>{
	@Query(value = "select email from candidates where email=?1",nativeQuery = true)
	Optional<HiringCandidate> isEmailExists(String email);

	
	@Query(value = "select * from candidates where id=?1",nativeQuery = true)
	Optional<HiringCandidate> isIdExists(long id);

	@Query(value = "select * from candidates where id=?1",nativeQuery = true)
     Optional<HiringCandidate> getCandidateProfileById(Long id);

	Optional<HiringCandidate> findById(Long id);


	Optional<HiringCandidate> findAllByemail(String email);
	

}
