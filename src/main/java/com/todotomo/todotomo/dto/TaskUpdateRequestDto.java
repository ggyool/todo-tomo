package com.todotomo.todotomo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel
@Getter
@NoArgsConstructor
public class TaskUpdateRequestDto {
    @ApiModelProperty(value = "항목 내용", dataType = "String")
    private String content;
    @ApiModelProperty(value = "항목의 상태 (doing, done)", dataType = "String")
    private String state;

    @Builder
    public TaskUpdateRequestDto(String content, String state){
        this.content = content;
        this.state = state;
    }

}
