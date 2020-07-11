package com.todotomo.todotomo.domain.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t ORDER BY t.createDate")
    List<Task> findAllAsc();

    @Query("SELECT t FROM Task t WHERE t.done=false ORDER BY t.createDate")
    List<Task> findTodoAsc();

    @Query("SELECT t FROM Task t WHERE t.done=true ORDER BY t.createDate")
    List<Task> findDoneAsc();
}
