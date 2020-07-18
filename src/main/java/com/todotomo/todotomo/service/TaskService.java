package com.todotomo.todotomo.service;

import com.todotomo.todotomo.domain.task.Task;
import com.todotomo.todotomo.domain.task.TaskRepository;
import com.todotomo.todotomo.domain.task.TaskState;
import com.todotomo.todotomo.domain.task.OrderType;
import com.todotomo.todotomo.domain.task.TasksType;
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

    public Long save(TaskSaveRequestDto taskSaveRequestDto){
//        if(content==null){
//            throw new IllegalArgumentException("잘못된 값이 입력되었습니다.");
//        }
        Task task = taskSaveRequestDto.toEntity();
        return taskRepository.save(task).getId();
    }

    public Long update(Long id, TaskUpdateRequestDto taskUpdateRequestDto){
        String content = taskUpdateRequestDto.getContent();
        TaskState state = taskUpdateRequestDto.getState();
//        if(content==null && state==null){
//            throw new IllegalArgumentException("값을 입력하지 않았습니다.");
//        }
//        if(!TaskState.checkValidate(state)){
//            throw new IllegalArgumentException("존재하지 않는 state입니다.");
//        }
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


    public List<Task> findSatisfiedList(String tasksType, String orderType){
//        if(!TasksType.checkValidate(tasksType)){
//            throw new IllegalArgumentException("존재하지 않는 항목들의 타입입니다.");
//        }
//        if(!OrderType.checkValidate(orderType)){
//            throw new IllegalArgumentException("존재하지 않는 정렬방식 입니다.");
//        }
        return taskRepository.findSatisfiedList(TasksType.convert(tasksType),
                OrderType.convert(orderType));
    }

    public void delete(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 id가 없습니다. id:" + id));
        taskRepository.delete(task);
    }

}
