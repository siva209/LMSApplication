package com.bridgelabz.lms.springbatch;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.lms.dto.CandidateHiredDTO;
import com.bridgelabz.lms.model.HiringCandidate;
import com.bridgelabz.lms.repository.CandidateRepository;


@Component
public class DBWriter implements ItemWriter<CandidateHiredDTO> {
   
	@Autowired
    private CandidateRepository candidateRepository;
	
	@Autowired
	private ModelMapper modelmapper;
	

    @Autowired
    public DBWriter (CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public void write(List<? extends CandidateHiredDTO> candidates) throws Exception{
        System.out.println("Data Saved for Users: " + candidates);
        
        for (CandidateHiredDTO candidate : candidates) {
        	
        	System.out.println(candidate);
        	
        	HiringCandidate lmshiring=modelmapper.map(candidates,HiringCandidate.class);
			lmshiring.setCreatorStamp(LocalDateTime.now());
			candidateRepository.save(lmshiring);
        	
        }
    }
}