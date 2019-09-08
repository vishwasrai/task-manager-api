package com.fse.taskmanager.repo;

import com.fse.taskmanager.entity.ParentTask;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentTasksRepository extends MongoRepository<ParentTask, String> {

    List<ParentTask> findByTask(String parentTask);
}
