package com.todotomo.todotomo.domain.task;

import com.todotomo.todotomo.domain.TimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Task extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable=false)
    private String content;

    @Column()
    private Boolean done;

    @Builder
    public Task(String content, Boolean done){
        this.content = content;
        this.done = done;
    }

    public void update(String content, Boolean done){
        if(content!=null) this.content = content;
        if(done!=null) this.done = done;
    }

    public String getContent() {
        return content;
    }
    public Boolean isDone() {
        return done;
    }
}
