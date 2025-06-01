package org.aryak.batch.config;

import org.aryak.batch.processors.FirstProcessor;
import org.aryak.batch.readers.FirstReader;
import org.aryak.batch.writers.FirstWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SecondJobConfig {

    private final FirstProcessor firstProcessor;
    private final FirstReader firstReader;
    private final FirstWriter firstWriter;
    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;

    public SecondJobConfig(FirstProcessor firstProcessor, FirstReader firstReader, FirstWriter firstWriter, PlatformTransactionManager platformTransactionManager, JobRepository jobRepository) {
        this.firstProcessor = firstProcessor;
        this.firstReader = firstReader;
        this.firstWriter = firstWriter;
        this.platformTransactionManager = platformTransactionManager;
        this.jobRepository = jobRepository;
    }

    @Bean
    public Job chunkedJob() {
        return new JobBuilder("Chunked Job", jobRepository)
                .start(task1())
                .build();
    }

    private Step task1() {
        return new StepBuilder("Chunked Step", jobRepository)
                .<Integer, Long>chunk(5, platformTransactionManager)
                .reader(firstReader)
                .processor(firstProcessor)
                .writer(firstWriter)
                .build();
    }


}
