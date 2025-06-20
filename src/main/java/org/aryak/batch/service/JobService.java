package org.aryak.batch.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobService {

    private final JobOperator jobOperator; // to stop the running job
    
    /**
     * e.g. pass key = abc and value = timestamp
     * Need to explore this more!!
     *
     * @return the job params
     */
    public JobParameters getJobParams() {
        JobParameter<String> p1 = new JobParameter<>("abc", String.class);
        JobParameter<Long> p2 = new JobParameter<>(System.currentTimeMillis(), Long.class);

        Map<String, JobParameter<?>> jobParams = new HashMap<>();
        jobParams.put("p1", p1);
        jobParams.put("p2", p2);

        return new JobParameters(jobParams);
    }

    /**
     * this code stops the job only after processing the current chunk completely.
     * Next chunk will not be picked for processing.
     * Processing cannot be stopped midway of any chunk!
     *
     * @param executionId from the BATCH_JOB_EXECUTION table
     */
    public void stopJob(long executionId) {

        try {
            jobOperator.stop(executionId);
        } catch (Exception e) {
            log.error("Exception while stopping job with execution ID : {}", executionId, e);
        }

    }
}
