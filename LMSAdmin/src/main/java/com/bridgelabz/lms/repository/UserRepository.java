package com.bridgelabz.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.lms.model.HiringCandidates;


@Repository
public interface UserRepository extends JpaRepository<HiringCandidates, Integer> {

}

