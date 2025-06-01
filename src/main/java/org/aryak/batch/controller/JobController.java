package org.aryak.batch.controller;

import org.aryak.batch.service.JobService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    public void triggerJob(@PathVariable String jobName) {
        jobService.launch(jobName);
    }

}
