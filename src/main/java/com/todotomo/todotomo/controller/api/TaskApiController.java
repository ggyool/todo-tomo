package com.todotomo.todotomo.controller.api;

import com.todotomo.todotomo.domain.task.Task;
import com.todotomo.todotomo.dto.TaskUpdateRequestDto;
import com.todotomo.todotomo.dto.TaskSaveRequestDto;
import com.todotomo.todotomo.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="/api/v1/tasks")
public class TaskApiController {

    private final TaskService taskService;

    @ApiOperation(value="조건에 맞는 항목을 생성일을 기준으로 정렬하여 가져옵니다.")
    @GetMapping
    public List<Task> findSatisfiedList(@ApiParam(value="all, doing, done", required = false)
                                        @RequestParam(value="tasksType", required=false, defaultValue="all") String tasksType,
                                        @ApiParam(value="asc, desc", required = false)
                                        @RequestParam(value="orderType", required=false, defaultValue="asc") String orderType){
        return taskService.findSatisfiedList(tasksType, orderType);
    }

    @ApiOperation(value="id 값을 이용하여 항목 하나를 가져옵니다.")
    @GetMapping("/{id}")
    public Task findById(@PathVariable Long id){
        return taskService.findById(id);
    }

    @ApiOperation(value="할 일을 목록에 저장합니다.")
    @ApiResponse(code = 500, message = "해당 id가 존재하지 않습니다.")
    @PostMapping
    public Long save(Authentication authentication, @RequestBody TaskSaveRequestDto tasksSaveRequestDto){
        System.out.println(authentication);
        return taskService.save(tasksSaveRequestDto);
    }

    @ApiOperation(value="id 값을 이용하여 항목을 수정합니다.")
    @PutMapping("/{id}")
    public Long update(@PathVariable Long id,
                       @RequestBody TaskUpdateRequestDto taskUpdateRequestDto){
        return taskService.update(id, taskUpdateRequestDto);
    }

    @ApiOperation(value="id 값을 이용하여 항목을 삭제합니다.")
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id){
        taskService.delete(id);
        return id;
    }
}
