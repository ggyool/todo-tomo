package com.todotomo.todotomo.domain.task;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TaskRepositoryTests {

    @Autowired
    TaskRepository taskRepository;

    @Test
    public void save(){
        String content = "dummy content";
        boolean done = false;

        for(int i=0; i<5; ++i){
            taskRepository.save(Task.builder().
                    content(content+i).done(done).build());
        }
        List<Task> tasks = taskRepository.findAllAsc();
        for(Task task : tasks){
            System.out.println(task.getContent() + "   " + task.isDone());
        }
    }
}