package com.fse.taskmanager.service;

import com.fse.taskmanager.entity.ParentTask;
import com.fse.taskmanager.entity.Task;
import com.fse.taskmanager.exception.ResourceNotFoundException;
import com.fse.taskmanager.model.TaskData;
import com.fse.taskmanager.model.TaskDetail;
import com.fse.taskmanager.repo.ParentTasksRepository;
import com.fse.taskmanager.repo.TasksRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskManagerService {

    @Autowired
    public ParentTasksRepository parentTasksRepository;
    @Autowired
    public TasksRepository tasksRepository;
    @Autowired
    private MongoOperations mongoOperation;

    public Task addTaskDetails(final TaskDetail taskDetail) {

        if (getTaskDetailsByTaskName(taskDetail.getTask()).isPresent()) {
            throw new ResourceNotFoundException("Task name already exists.");
        }
        return createTask(taskDetail);
    }

    public Task modifyTaskDetails(final String taskId, final TaskDetail taskDetail) {

        return getTaskDetailsByTaskId(taskId).map(task -> {
            task.setTaskName(taskDetail.getTask());
            task.getParentTask().setTask(taskDetail.getParentTask());
            task.setPriority(taskDetail.getPriority());
            task.setStartDate(taskDetail.getStartDate());
            task.setEndDate(taskDetail.getEndDate());
            return tasksRepository.save(task);
        }).orElseThrow(() -> new ResourceNotFoundException("Task name not found."));
    }

    public Task deleteTask(final String taskName) {

        Optional<Task> task = getTaskDetailsByTaskName(taskName);
        if (!task.isPresent()) {
            throw new ResourceNotFoundException("Task name does not exists.");
        }
        tasksRepository.delete(task.get());
        return task.get();
    }

    public List<TaskData> findTaskDetailsByTaskName(final String taskName) {
        Task task = getTaskDetailsByTaskName(taskName).orElseThrow(() ->
                new ResourceNotFoundException("Task name not found."));
        return Arrays.asList(TaskData.builder()
                .task(task.getTaskName())
                .parentTask(StringUtils.isEmpty(task.getParentTask()) ? "N/A" : task.getParentTask().getTask())
                .priority(task.getPriority())
                .startDate(task.getStartDate())
                .endDate(task.getEndDate())
                .build());
    }

    /*public List<Task> findTaskDetailsByParentTaskName(final String parentTaskName) {
        Optional<ParentTask> parentTask = Optional.ofNullable(getParentTaskDetails(parentTaskName))
                .orElseThrow(() -> new ResourceNotFoundException("Parent Task name not found."));
        List<Task> task = getTaskDetailsByParentTaskId(parentTask.get().getId());

        return task;
    }*/

    public List<TaskData> findTaskDetails(final TaskDetail taskDetail) {

        Query findByQuery = new Query();
        List<ParentTask> tasksForParents = getParentTaskDetails(taskDetail.getParentTask());

        List<Criteria> criteriaList = new ArrayList<>();
        if (taskDetail.getTask() != null) {
            criteriaList.add(Criteria.where("taskName").in(taskDetail.getTask()));
        }
        if (taskDetail.getParentTask() != null && !tasksForParents.isEmpty()) {
            criteriaList.add(Criteria.where("parentTask").in(tasksForParents));
        }
        if (taskDetail.getPriorityFrom() != 0) {
            criteriaList.add(Criteria.where("priority").gte(taskDetail.getPriorityFrom()));
        }
        if (taskDetail.getPriorityTo() != 0) {
            criteriaList.add(Criteria.where("priority").lte(taskDetail.getPriorityTo()));
        }
        if (taskDetail.getStartDate() != null) {
            criteriaList.add(Criteria.where("startDate").in(taskDetail.getStartDate()));
        }
        if (taskDetail.getEndDate() != null) {
            criteriaList.add(Criteria.where("endDate").in(taskDetail.getEndDate()));
        }
        findByQuery.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()])));
        List<Task> tasks = mongoOperation.find(findByQuery, Task.class);

        return tasks.stream().map(task -> TaskData.buildTaskData(task))
                .collect(Collectors.toList());
    }

    private Task createTask(final TaskDetail taskDetail) {
        if (StringUtils.isEmpty(taskDetail.getParentTask())) {
            return tasksRepository.save(toTasksDataMapper(taskDetail, null));
        } else {
            return tasksRepository.findByTaskName(taskDetail.getParentTask()).map(task -> {
                val parentTask = parentTasksRepository.save(toParentTasksDataMapper(task));
                return tasksRepository.save(toTasksDataMapper(taskDetail, parentTask));
            }).orElseThrow(() -> new ResourceNotFoundException("Invalid Parent Task name provided."));
        }
    }

    private Optional<Task> getTaskDetailsByTaskId(final String taskId) {
        return tasksRepository.findById(taskId);
    }

    private Optional<Task> getTaskDetailsByTaskName(final String taskName) {
        return tasksRepository.findByTaskName(taskName);
    }

    private List<ParentTask> getParentTaskDetails(final String parentTaskName) {
        return parentTasksRepository.findByTask(parentTaskName);
    }

    /*private List<Task> getTaskDetailsByParentTaskId(final String parentTaskId) {
        return tasksRepository.findByParentTaskId(parentTaskId);
    }*/

    private ParentTask toParentTasksDataMapper(Task task) {
        return ParentTask.builder()
                .task(task.getTaskName())
                .build();
    }

    private Task toTasksDataMapper(TaskDetail taskDetail, ParentTask parentTask) {
        return Task.builder()
                .taskName(taskDetail.getTask())
                .parentTask(parentTask)
                .priority(taskDetail.getPriority())
                .startDate(taskDetail.getStartDate())
                .endDate(taskDetail.getEndDate())
                .build();
    }
}
