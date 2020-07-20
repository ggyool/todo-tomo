package com.todotomo.todotomo.service;

import com.todotomo.todotomo.domain.task.Task;
import com.todotomo.todotomo.domain.task.TaskRepository;
import com.todotomo.todotomo.domain.task.TaskState;
import com.todotomo.todotomo.domain.task.OrderType;
import com.todotomo.todotomo.domain.task.TasksType;
import com.todotomo.todotomo.domain.user.User;
import com.todotomo.todotomo.domain.user.UserRepository;
import com.todotomo.todotomo.dto.TaskSaveRequestDto;
import com.todotomo.todotomo.dto.TaskUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public Long save(TaskSaveRequestDto taskSaveRequestDto, Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("해당 user가 없습니다. userId:" + userId));
        Task task = taskSaveRequestDto.toEntity(user);
        return taskRepository.save(task).getId();
    }

    public Long update(Long id, TaskUpdateRequestDto taskUpdateRequestDto){
        String content = taskUpdateRequestDto.getContent();
        TaskState state = taskUpdateRequestDto.getState();
        if(content==null && state==null)
            throw new IllegalArgumentException("잘못된 입력입니다.");
        Task task = taskRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 id가 없습니다. id:" + id));
        task.update(content, state);
        return id;
    }

    public Task findById(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 id가 없습니다. id:" + id));
        return task;
    }


    public List<Task> findSatisfiedList(String tasksType, String orderType, Long userId){
        return taskRepository.findSatisfiedList(TasksType.convert(tasksType),
                OrderType.convert(orderType), userId);
    }

    public void delete(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 id가 없습니다. id:" + id));
        taskRepository.delete(task);
    }

}
