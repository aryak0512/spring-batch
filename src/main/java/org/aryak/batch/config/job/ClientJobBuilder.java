package org.aryak.batch.config.job;

import lombok.AllArgsConstructor;
import org.aryak.batch.config.step.ClientStepBuilder;
import org.aryak.batch.model.Client;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.stereotype.Component;

/**
 * Bean that defines the job construct
 */
@Component
@AllArgsConstructor
public class ClientJobBuilder {

    private final ClientStepBuilder stepBuilder;
    private final JobRepository jobRepository;

    public Job buildJob(Client config) {
        String jobName = "job-" + config.getClientId();
        return new JobBuilder(jobName, jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(stepBuilder.buildStep(config))
                .build();
    }
}
