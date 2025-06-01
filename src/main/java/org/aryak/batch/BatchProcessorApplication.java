package org.aryak.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@EnableBatchProcessing
@SpringBootApplication
public class BatchProcessorApplication implements CommandLineRunner {

    JobLauncher jobLauncher;
    Job myJob;

    public BatchProcessorApplication(JobLauncher jobLauncher, Job myJob) {
        this.jobLauncher = jobLauncher;
        this.myJob = myJob;
    }

    public static void main(String[] args) {
        SpringApplication.run(BatchProcessorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        JobParameter<String> p1 = new JobParameter<>("jp1", String.class);
        JobParameter<Long> p2 = new JobParameter<>(System.currentTimeMillis(), Long.class);

        Map<String, JobParameter<?>> jobParams = new HashMap<>();
        jobParams.put("p1", p1);
        jobParams.put("p2", p2);

        JobParameters parameters = new JobParameters(jobParams);

        jobLauncher.run(myJob, parameters);
    }

}
