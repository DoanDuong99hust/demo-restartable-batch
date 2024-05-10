package com.example.demorestartablejob.job.batch;

import com.example.demorestartablejob.job.RestartableReader;
import com.example.demorestartablejob.model.UserDTO;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.PathResource;


public class UserItemReader extends FlatFileItemReader<UserDTO> {
    public UserItemReader() {
        super();
        this.setResource(new PathResource("input-file/user.csv"));
        this.setLineMapper(lineMapper());
    }

    private DefaultLineMapper<UserDTO> lineMapper() {
        DefaultLineMapper<UserDTO> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer());
        lineMapper.setFieldSetMapper(fieldSetMapper());
        return lineMapper;
    }

    private DelimitedLineTokenizer tokenizer() {
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");
        tokenizer.setNames("age","firstName","lastName");

        return tokenizer;
    }

    private BeanWrapperFieldSetMapper<UserDTO> fieldSetMapper() {
        BeanWrapperFieldSetMapper<UserDTO> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(UserDTO.class);
        return fieldSetMapper;
    }
}
