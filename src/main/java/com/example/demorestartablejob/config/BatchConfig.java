package com.example.demorestartablejob.config;

import com.example.demorestartablejob.job.batch.UserItemProcessor;
import com.example.demorestartablejob.job.batch.UserItemReader;
import com.example.demorestartablejob.job.batch.UserItemWriter;
import com.example.demorestartablejob.model.UserDTO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    public BatchConfig(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
    }

    @Bean
    public Job processUserFileJob() throws Exception {
        return new JobBuilder("demoJob", jobRepository)
                .start(processUserFileStep())
                .build();
    }

    @Bean
    public Step processUserFileStep() throws Exception {
        return new StepBuilder("demo2", jobRepository)
                .<UserDTO, UserDTO>chunk(5, platformTransactionManager)
                .reader(itemReader())
                .processor(new UserItemProcessor())
                .writer(new UserItemWriter())
                .build();

    }

    @Bean
    @StepScope
    public FlatFileItemReader<UserDTO> itemReader() {
        return new UserItemReader();
    }
}
