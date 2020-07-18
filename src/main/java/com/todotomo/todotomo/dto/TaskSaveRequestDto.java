package com.todotomo.todotomo.dto;

import com.todotomo.todotomo.domain.task.Task;
import com.todotomo.todotomo.domain.task.TaskState;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;

@ApiModel
@NoArgsConstructor
public class TaskSaveRequestDto {
    @ApiModelProperty(value = "항목 내용", dataType = "String")
    private String content;

    @Builder
    public TaskSaveRequestDto(String content){
        this.content = content;
    }

    public Task toEntity(){
        return Task.builder().content(content).state(TaskState.DOING).build();
    }
    public String getContent() {
        return content;
    }
}
