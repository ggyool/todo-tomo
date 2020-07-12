package com.todotomo.todotomo.controller.api;

import com.todotomo.todotomo.dto.TaskUpdateRequestDto;
import com.todotomo.todotomo.dto.TaskSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class TaskApiControllerTesta {

    @Autowired
    TaskApiController taskApiController;

    @Test
    public void updateTest(){
        String content = "before content";
        Long id = taskApiController.save(TaskSaveRequestDto.builder().content(content).build());

        String updateContent = "after content";
        String updateState = "done";
        TaskUpdateRequestDto taskUpdateRequestDto = TaskUpdateRequestDto.builder()
                .content(updateContent)
                .state(updateState)
                .build();
        taskApiController.update(id, taskUpdateRequestDto);

        assertThat(taskApiController.findById(id).getContent().equals(updateContent));
        assertThat(taskApiController.findById(id).getState().equals(updateState));
    }


}