package com.bridgelabz.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.lms.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long>{

	Optional<Status> findById(int id);

	Optional<Status> isIdExists(long id);

}
