package com.todotomo.todotomo.domain.task;

import com.todotomo.todotomo.domain.TimeEntity;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@ApiModel
@Getter
@NoArgsConstructor
@Entity
public class Task extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable=false)
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskState state;

    @Builder
    public Task(String content, TaskState state){
        this.content = content;
        this.state = state;
    }

    public void update(String content, String state){
        if(content!=null) this.content = content;
        if(state!=null) this.state = TaskState.convert(state);
    }

    public String getContent() {
        return content;
    }
    public TaskState getState() {
        return state;
    }
}
