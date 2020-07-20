package com.todotomo.todotomo.domain.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>, TaskCustomRepository {
}
