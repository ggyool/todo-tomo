package com.todotomo.todotomo.controller.api;

import com.todotomo.todotomo.domain.task.Task;
import com.todotomo.todotomo.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="/api/v1/task")
public class TaskApiController {

    private final TaskService taskService;

    @ApiOperation(value="모든 항목을 생성일을 기준으로 정렬하여 가져옵니다.")
    @GetMapping("/all")
    public List<Task> findAllAsc(){
        return taskService.findAllAsc();
    }
    @ApiOperation(value="완료된 항목을 생성일을 기준으로 정렬하여 가져옵니다.")
    @GetMapping("/done")
    public List<Task> findDoneAsc(){
        return taskService.findDoneAsc();
    }
    @ApiOperation(value="진행중인 항목을 생성일을 기준으로 정렬하여 가져옵니다.")
    @GetMapping("/todo")
    public List<Task> findTodoAsc(){
        return taskService.findTodoAsc();
    }
    @ApiOperation(value="id 값을 이용하여 항목 하나를 가져옵니다.")
    @ApiResponse(code = 500, message = "해당 id가 존재하지 않습니다.")
    @GetMapping("/{id}")
    public Task findById(@PathVariable Long id){
        return taskService.findById(id);
    }

    @ApiOperation(value="할 일을 목록에 저장합니다.")
    @ApiResponse(code = 500, message = "해당 id가 존재하지 않습니다.")
    @PostMapping()
    public Long save(String content){
        return taskService.save(content);
    }

    @ApiOperation(value="id 값을 이용하여 항목을 수정합니다.")
    @ApiResponse(code = 500, message = "해당 id가 존재하지 않거나, 수정값이 올바르지 않습니다.")
    @PutMapping("/{id}")
    public Long update(@PathVariable Long id,
                       @RequestParam(name="content", required=false) String content,
                       @RequestParam(name="done", required = false) Boolean done){
        System.out.println(content + "  " + done);
        return taskService.update(id, content, done);
    }

    @ApiOperation(value="id 값을 이용하여 항목을 삭제합니다.")
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id){
        taskService.delete(id);
        return id;
    }
}
