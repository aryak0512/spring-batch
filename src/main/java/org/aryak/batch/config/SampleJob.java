package org.aryak.batch.config;

import lombok.extern.slf4j.Slf4j;
import org.aryak.batch.listeners.job.MyJobListener;
import org.aryak.batch.listeners.step.MyStepListener;
import org.aryak.batch.tasks.FirstTask;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;

@Slf4j
@Configuration
public class SampleJob {

    private final MyJobListener myJobListener;
    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;

    public SampleJob(MyJobListener myJobListener, PlatformTransactionManager platformTransactionManager, JobRepository jobRepository) {
        this.myJobListener = myJobListener;
        this.platformTransactionManager = platformTransactionManager;
        this.jobRepository = jobRepository;
    }

    @Bean
    public Job firstJob() {
        return new JobBuilder("My first Job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .next(step2())
                .listener(myJobListener)
                .build();
    }

    private Step step1() {

        log.info("Inside step 1 bean declaration..");
        return new StepBuilder("Step 1", jobRepository)
                .listener(new MyStepListener())
                .tasklet(new FirstTask(), platformTransactionManager)
                .build();

    }

    private Step step2() {

        log.info("Inside step 2 bean declaration..");
        return new StepBuilder("Step 2", jobRepository)
                .listener(new MyStepListener())
                .tasklet((contribution, chunkContext) -> {

                    // task code goes here
                    log.info("In tasklet of step 2..");

                    // grab the job execution context
                    Map<String, Object> jobExecutionContext = chunkContext.getStepContext().getJobExecutionContext();
                    log.info("Job execution context : {}", jobExecutionContext);

                    return RepeatStatus.FINISHED;
                }, platformTransactionManager).build();

    }
}
