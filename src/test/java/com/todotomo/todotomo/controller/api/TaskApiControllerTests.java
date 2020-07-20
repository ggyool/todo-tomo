package com.todotomo.todotomo.controller.api;

import com.todotomo.todotomo.domain.task.TaskState;
import com.todotomo.todotomo.domain.user.User;
import com.todotomo.todotomo.domain.user.UserRepository;
import com.todotomo.todotomo.dto.TaskUpdateRequestDto;
import com.todotomo.todotomo.dto.TaskSaveRequestDto;
import com.todotomo.todotomo.service.CustomUserDetailService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class TaskApiControllerTests {

    @Autowired
    TaskApiController taskApiController;
//    @Autowired
//    UserRepository userRepository;
    @Test
    public void updateTest(){
        String content = "before content";


//        User user =userRepository.findByEmail("bbb@bbb.com").orElse(null);
//        System.out.println(user.getEmail());
//        System.out.println(user.getRole().getAuthority());

//        Long id = taskApiController.save(TaskSaveRequestDto.builder().content(content).build());
//
//        String updateContent = "after content";
//        TaskState updateState = TaskState.DOING;
//        TaskUpdateRequestDto taskUpdateRequestDto = TaskUpdateRequestDto.builder()
//                .content(updateContent)
//                .state(updateState)
//                .build();
//        taskApiController.update(id, taskUpdateRequestDto);
//
//        assertThat(taskApiController.findById(id).getContent().equals(updateContent));
//        assertThat(taskApiController.findById(id).getState().equals(updateState));
    }


}