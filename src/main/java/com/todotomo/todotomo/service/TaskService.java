package com.todotomo.todotomo.service;

import com.todotomo.todotomo.domain.task.Task;
import com.todotomo.todotomo.domain.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Transactional
    public Long save(String content){
        Task task = Task.builder().content(content).done(false).build();
        return taskRepository.save(task).getId();
    }

    @Transactional
    public Long update(Long id, String content, Boolean done){
        if(content==null && done==null){
            throw new IllegalArgumentException("수정할 값을 입력하지 않았습니다.");
        }
        Task task = taskRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 id가 없습니다. id:" + id));
        task.update(content, done);
        return id;
    }

    public Task findById(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 id가 없습니다. id:" + id));
        return task;
    }

    @Transactional
    public List<Task> findAllAsc(){
        return taskRepository.findAllAsc();
    }

    @Transactional
    public List<Task> findTodoAsc(){
        return taskRepository.findTodoAsc();
    }

    @Transactional
    public List<Task> findDoneAsc(){
        return taskRepository.findDoneAsc();
    }

    public void delete(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 id가 없습니다. id:" + id));
        taskRepository.delete(task);
    }

}
