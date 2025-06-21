package org.aryak.batch.config.job;

import lombok.RequiredArgsConstructor;
import org.aryak.batch.config.ClientMetadata;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientJobLauncher {

    private final JobLauncher jobLauncher;
    private final ClientMetadata clientMetadata;

    public void runJob(Long clientId) {
        try {
            JobParameters parameters = new JobParametersBuilder()
                    .addString("timestamp", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();

            Job job = clientMetadata.getClientJob(clientId);
            jobLauncher.run(job, parameters);

        } catch (Exception e) {
            throw new RuntimeException("Failed to run job for client " + clientId, e);
        }
    }
}
