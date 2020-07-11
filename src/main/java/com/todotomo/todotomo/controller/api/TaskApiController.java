package com.todotomo.todotomo.controller.api;

import com.todotomo.todotomo.domain.task.Task;
import com.todotomo.todotomo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="/api/v1/task")
public class TaskApiController {

    private final TaskService taskService;

    @GetMapping("/all")
    public List<Task> findAllAsc(){
        return taskService.findAllAsc();
    }
    @GetMapping("/done")
    public List<Task> findDoneAsc(){
        return taskService.findDoneAsc();
    }
    @GetMapping("/todo")
    public List<Task> findTodoAsc(){
        return taskService.findTodoAsc();
    }
    @GetMapping("/{id}")
    public Task findById(@PathVariable Long id){
        return taskService.findById(id);
    }

    @PostMapping()
    public Long save(String content){
        return taskService.save(content);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id,
                       @RequestParam(name="content", required=false) String content,
                       @RequestParam(name="done", required = false) Boolean done){
        System.out.println(content + "  " + done);
        return taskService.update(id, content, done);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id){
        taskService.delete(id);
        return id;
    }
}
