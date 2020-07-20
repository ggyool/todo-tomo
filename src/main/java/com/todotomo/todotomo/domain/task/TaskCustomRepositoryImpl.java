package com.todotomo.todotomo.domain.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    public List<Task> findSatisfiedList(TasksType tasksType, OrderType orderType, Long userId) {
        if(tasksType.equals(TasksType.ALL)) {
            if(orderType.equals(OrderType.ASC)) return findAllAsc(userId);
            if(orderType.equals(OrderType.DESC)) return findAllDesc(userId);
        }
        if(tasksType.equals(TasksType.DOING)){
            if(orderType.equals(OrderType.ASC)) return findDoingAsc(userId);
            if(orderType.equals(OrderType.DESC)) return findDoingDesc(userId);
        }
        if(tasksType.equals(TasksType.DONE)){
            if(orderType.equals(OrderType.ASC)) return findDoneAsc(userId);
            if(orderType.equals(OrderType.DESC)) return findDoneDesc(userId);
        }
        return Collections.emptyList();
    }
    private List<Task> findAllAsc(Long userId){
        return entityManager.createQuery("SELECT t FROM Task t WHERE t.user.id=:userId ORDER BY t.createDate", Task.class)
                .setParameter("userId", userId)
                .getResultList();
    }
    private List<Task> findAllDesc(Long userId){
        return entityManager.createQuery("SELECT t FROM Task t WHERE t.user.id=:userId ORDER BY t.createDate DESC", Task.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    private List<Task> findDoingAsc(Long userId){
        return entityManager.createQuery("SELECT t FROM Task t WHERE t.user.id=:userId AND t.state='DOING' ORDER BY t.createDate", Task.class)
                .setParameter("userId", userId)
                .getResultList();
    }
    private List<Task> findDoingDesc(Long userId){
        return entityManager.createQuery("SELECT t FROM Task t WHERE t.user.id=:userId AND t.state='DOING' ORDER BY t.createDate DESC", Task.class)
                .setParameter("userId", userId)
                .getResultList();
    }
    private List<Task> findDoneAsc(Long userId){
        return entityManager.createQuery("SELECT t FROM Task t WHERE t.user.id=:userId AND t.state='DONE' ORDER BY t.createDate", Task.class)
                .setParameter("userId", userId)
                .getResultList();
    }
    private List<Task> findDoneDesc(Long userId){
        return entityManager.createQuery("SELECT t FROM Task t WHERE t.user.id=:userId AND t.state='DONE' ORDER BY t.createDate DESC", Task.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
