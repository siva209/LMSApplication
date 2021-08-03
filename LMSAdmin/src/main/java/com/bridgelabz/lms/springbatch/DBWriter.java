package com.bridgelabz.lms.springbatch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.lms.model.HiringCandidates;
import com.bridgelabz.lms.repository.UserRepository;

@Component
public class DBWriter implements ItemWriter<HiringCandidates> {

    private UserRepository userRepository;

    @Autowired
    public DBWriter (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void write(List<? extends HiringCandidates> candidates) throws Exception{
        System.out.println("Data Saved for Users: " + candidates);
        
        for (HiringCandidates candidate : candidates) {
        	System.out.println(candidate);
        	userRepository.save(candidate);
        }
    }
}
