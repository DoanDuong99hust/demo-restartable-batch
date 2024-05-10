package com.example.demorestartablejob.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {

    private final JobLauncher jobLauncher;
    private final Job processUserFileJob;

    public JobController(JobLauncher jobLauncher, Job processUserFileJob) {
        this.jobLauncher = jobLauncher;
        this.processUserFileJob = processUserFileJob;
    }

    @GetMapping("/user")
    public ResponseEntity<Void> manualLaunchUserJob() {
        JobParameters jobParameters = new JobParametersBuilder()
                        .toJobParameters();

        try {
            jobLauncher.run(processUserFileJob, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobParametersInvalidException |
                 JobInstanceAlreadyCompleteException | JobRestartException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
