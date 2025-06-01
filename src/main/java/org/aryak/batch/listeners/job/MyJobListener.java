package org.aryak.batch.listeners.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        var jobId = jobExecution.getJobId();
        log.info("Before job triggered for jobId : {}", jobId);
        // grab the job execution context
        jobExecution.getExecutionContext().put("time taken by task 1", System.currentTimeMillis());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        var jobId = jobExecution.getJobId();
        log.info("After job triggered for jobId : {}", jobId);
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Successfully ran jobId : {}", jobId);
        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            log.error("Failed run for jobId : {}", jobId);
        }

    }
}
