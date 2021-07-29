package com.bridgelabz.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.lms.model.QualificationInfo;
@Repository
public interface QualificationRepository extends JpaRepository<QualificationInfo, Integer>{

}
