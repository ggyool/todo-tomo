package com.todotomo.todotomo.domain.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Transactional
@Repository
public class TaskCustomRepositoryImpl implements TaskCustomRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Task> findSatisfiedList(TasksType tasksType, OrderType orderType) {
        if(tasksType.equals(TasksType.ALL)) {
            if(orderType.equals(OrderType.ASC)) return findAllAsc();
            if(orderType.equals(OrderType.DESC)) return findAllDesc();
        }
        if(tasksType.equals(TasksType.DOING)){
            if(orderType.equals(OrderType.ASC)) return findDoingAsc();
            if(orderType.equals(OrderType.DESC)) return findDoingDesc();
        }
        if(tasksType.equals(TasksType.DONE)){
            if(orderType.equals(OrderType.ASC)) return findDoneAsc();
            if(orderType.equals(OrderType.DESC)) return findDoneDesc();
        }
        return Collections.emptyList();
    }
    private List<Task> findAllAsc(){
        return entityManager.createQuery("SELECT t FROM Task t ORDER BY t.createDate", Task.class).getResultList();
    }
    private List<Task> findAllDesc(){
        return entityManager.createQuery("SELECT t FROM Task t ORDER BY t.createDate DESC", Task.class).getResultList();
    }

    private List<Task> findDoingAsc(){
        return entityManager.createQuery("SELECT t FROM Task t WHERE t.state='DOING' ORDER BY t.createDate", Task.class).getResultList();
    }
    private List<Task> findDoingDesc(){
        return entityManager.createQuery("SELECT t FROM Task t WHERE t.state='DOING' ORDER BY t.createDate DESC", Task.class).getResultList();
    }
    private List<Task> findDoneAsc(){
        return entityManager.createQuery("SELECT t FROM Task t WHERE t.state='DONE' ORDER BY t.createDate", Task.class).getResultList();
    }
    private List<Task> findDoneDesc(){
        return entityManager.createQuery("SELECT t FROM Task t WHERE t.state='DONE' ORDER BY t.createDate DESC", Task.class).getResultList();
    }
}
