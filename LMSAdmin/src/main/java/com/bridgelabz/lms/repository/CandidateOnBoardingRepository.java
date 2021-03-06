package com.bridgelabz.lms.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bridgelabz.lms.model.CandidateOnboardingDetails;
@Repository
public interface CandidateOnBoardingRepository extends JpaRepository<CandidateOnboardingDetails, Long>{

	
	@Query(value = "select email from candidates_on_boarding where email=?1",nativeQuery = true)
	Optional<CandidateOnboardingDetails> isEmailExists(String email);
	
	@Query(value = "select * from candidates_on_boarding where id=?1",nativeQuery = true)
	Optional<CandidateOnboardingDetails> isIdExists(long id);
}

