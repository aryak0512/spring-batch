package org.aryak.batch.v2;

import org.aryak.batch.model.ClientConfig;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.stereotype.Component;

@Component
public class ClientJobBuilder {

    private final ClientStepBuilder stepBuilder;
    private final JobRepository jobRepository;

    public ClientJobBuilder(ClientStepBuilder stepBuilder, JobRepository jobRepository) {

        this.stepBuilder = stepBuilder;
        this.jobRepository = jobRepository;
    }

    public Job buildJob(ClientConfig config) {
        return new JobBuilder("job-" + config.clientId(), jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(stepBuilder.buildStep(config))
                .build();
    }
}
