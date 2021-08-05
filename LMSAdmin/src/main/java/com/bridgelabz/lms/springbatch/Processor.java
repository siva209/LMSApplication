package com.bridgelabz.lms.springbatch;

import java.time.LocalDateTime;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.bridgelabz.lms.dto.CandidateHiredDTO;


@Component
public class Processor implements ItemProcessor<CandidateHiredDTO, CandidateHiredDTO> {

    @Override
    public CandidateHiredDTO process(CandidateHiredDTO user) throws Exception {
    	 user.setCreatorStamp(LocalDateTime.now());
    	 user.setUpdateStamp(LocalDateTime.now());
        return user;
    }
}
