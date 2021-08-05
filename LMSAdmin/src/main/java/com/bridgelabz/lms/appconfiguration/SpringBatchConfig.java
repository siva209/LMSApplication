package com.bridgelabz.lms.appconfiguration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.bridgelabz.lms.dto.CandidateHiredDTO;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<CandidateHiredDTO> itemReader,
                   ItemProcessor<CandidateHiredDTO, CandidateHiredDTO> itemProcessor,
                   ItemWriter<CandidateHiredDTO> itemWriter
    ) {

        Step step = stepBuilderFactory.get("ETL-file-load")
                .<CandidateHiredDTO, CandidateHiredDTO>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();


        return jobBuilderFactory.get("ETL-Load")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public FlatFileItemReader<CandidateHiredDTO> itemReader() {
    	 FlatFileItemReader<CandidateHiredDTO> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new FileSystemResource("F:\\CODE\\23-05-2021\\SBDemo\\SpringProjects\\LMSAdmin\\src\\main\\resources\\candidate_data.csv"));
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }
 
    @Bean
    public LineMapper<CandidateHiredDTO> lineMapper() {
    	 DefaultLineMapper<CandidateHiredDTO> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id","firstName","middleName","lastName","email","mobileNum","hiredCity","parentName","parentMobile","temporaryAddress","parentOccupation","parentAnnualSalary","permanentAddress");
        BeanWrapperFieldSetMapper<CandidateHiredDTO> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(CandidateHiredDTO.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

}
