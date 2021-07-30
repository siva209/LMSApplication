package com.bridgelabz.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.lms.model.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

}

