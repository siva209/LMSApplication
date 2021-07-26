package com.bridgelabz.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.bridgelabz.lms.model.CandidateOnboardingDetails;
@Repository
public interface CandidateOnBoardingRepository extends JpaRepository<CandidateOnboardingDetails, Long>{

//	
	@Query(value = "select email from candidates_on_boarding where email=?1",nativeQuery = true)
	Optional<CandidateOnboardingDetails> isEmailExists(String email);
//
//	Optional<CandidateOnboardingDetails> isIdExists(long id);
//
	Optional<CandidateOnboardingDetails> findById(Long id);
	Optional<CandidateOnboardingDetails> isIdExists(long id);
}
