package com.fse.taskmanager.repo;

import com.fse.taskmanager.entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TasksRepository extends MongoRepository<Task, String> {

    Optional<Task> findByTaskName(String task);

    List<Task> findByParentTaskId(String parentTaskId);

    /*@Query("select t from task t where (:taskName is null or t.task in :taskName) " +
            "and (:parentTaskName is null or t.parentTask.task in :parentTaskName) " +
            "and (:priorityFrom = 0 or :priorityTo = 0 or t.priority between :priorityFrom and :priorityTo) " +
            "and (:startDate is null or t.startDate in :startDate) " +
            "and (:endDate is null or t.endDate in :endDate) ")*/
    /*List<Task> findByTaskDetails(@Param("taskName") String taskName, @Param("parentTaskName") String parentTaskName,
                                 @Param("priorityFrom") Integer priorityFrom, @Param("priorityTo") Integer priorityTo,
                                 @Param("startDate") String startDate, @Param("endDate") String endDate);*/
    /*List<Task> findByTaskDetails(String taskName, String parentTaskName, Integer priorityFrom,
                                 Integer priorityTo);*/
}
