package com.bridgelabz.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.lms.model.BankInfo;
import com.bridgelabz.lms.model.FileDB;

@Repository
public interface BankRepository  extends JpaRepository<BankInfo, Integer>{

	Optional<BankInfo> findById(Long id);

	FileDB save(FileDB fileDB);

	Optional<BankInfo> findById(String id);


}
