package com.example.demorestartablejob.job.batch;

import com.example.demorestartablejob.model.UserDTO;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class UserItemWriter implements ItemWriter<UserDTO> {

    @Override
    public void write(Chunk<? extends UserDTO> chunk) throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output-file/user-out.csv",true))) {
            for (UserDTO item : chunk.getItems()) {
                writer.write(item.getFirstName() + "," + item.getAge() + "\n");
            }
        }
    }
}
