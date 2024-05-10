package com.example.demorestartablejob.job.batch;

import com.example.demorestartablejob.model.UserDTO;
import org.springframework.batch.item.ItemProcessor;

public class UserItemProcessor implements ItemProcessor<UserDTO, UserDTO> {

    @Override
    public UserDTO process(UserDTO item) throws Exception {
        return item;
    }
}
