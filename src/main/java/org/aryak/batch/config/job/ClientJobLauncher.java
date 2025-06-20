package org.aryak.batch.config.job;

import org.aryak.batch.config.BrokerMetadata;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

@Component
public class ClientJobLauncher {

    private final JobLauncher jobLauncher;

    private final BrokerMetadata brokerMetadata;

    public ClientJobLauncher(JobLauncher jobLauncher, BrokerMetadata brokerMetadata) {
        this.jobLauncher = jobLauncher;
        this.brokerMetadata = brokerMetadata;
    }

    public void runJob(String clientId) {
        try {
            JobParameters parameters = new JobParametersBuilder()
                    .addString("timestamp", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();

            Job job = brokerMetadata.getClientJob(clientId);
            JobExecution execution = jobLauncher.run(job, parameters);
            ExecutionContext executionContext = execution.getExecutionContext();
            System.out.println(executionContext.toMap());


        } catch (Exception e) {
            throw new RuntimeException("Failed to run job for client " + clientId, e);
        }
    }
}
