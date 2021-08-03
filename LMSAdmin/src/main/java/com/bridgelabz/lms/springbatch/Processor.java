package com.bridgelabz.lms.springbatch;

import java.time.LocalDateTime;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.bridgelabz.lms.model.HiringCandidates;



@Component
public class Processor implements ItemProcessor<HiringCandidates, HiringCandidates> {

    @Override
    public HiringCandidates process(HiringCandidates user) throws Exception {
    	 user.setCreatorStamp(LocalDateTime.now());
    	 user.setUpdateStamp(LocalDateTime.now());
        return user;
    }
}
