package com.todotomo.todotomo.domain.task;

import com.todotomo.todotomo.domain.TimeEntity;
import com.todotomo.todotomo.domain.user.User;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@ApiModel
@NoArgsConstructor
@Entity
public class Task extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable=false)
    private String content;

    @Column(nullable = false)// , columnDefinition = "varchar(255) default 'DOING'")
    @Enumerated(EnumType.STRING)
    private TaskState state;

    //@Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @Builder
    public Task(String content, TaskState state, User user){
        this.content = content;
        this.state = state;
        this.user = user;
    }

    public void update(String content, TaskState state){
        if(content!=null) this.content = content;
        if(state!=null) this.state = state;
    }

    // 안 만드는 방법 없을까
    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public TaskState getState() {
        return state;
    }
}
