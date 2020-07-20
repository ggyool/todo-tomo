package com.todotomo.todotomo.domain.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskCustomRepository {
    public List<Task> findSatisfiedList(TasksType tasksType, OrderType orderType, Long userId);
}
