package com.fse.taskmanager.controller;

import com.fse.taskmanager.entity.Task;
import com.fse.taskmanager.model.TaskData;
import com.fse.taskmanager.model.TaskDetail;
import com.fse.taskmanager.service.TaskManagerService;
import com.fse.taskmanager.util.DateHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("/task-manager")
public class TaskManagerController {

    private final TaskManagerService taskManagerService;

    @PostMapping(value = "/addTask")
    @ResponseStatus(HttpStatus.OK)
    public Task addTaskDetails(@RequestBody @Valid final TaskDetail taskDetail) {
        return taskManagerService.addTaskDetails(taskDetail);
    }

    @PutMapping(value = "/updateTask/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public Task modifyTaskDetails(@PathVariable final String taskId, @RequestBody @Valid final TaskDetail taskDetail) {
        return taskManagerService.modifyTaskDetails(taskId, taskDetail);
    }

    @DeleteMapping(value = "/deleteTask/{taskName}")
    @ResponseStatus(HttpStatus.OK)
    public Task removeTaskDetail(@PathVariable final String taskName) {
        return taskManagerService.deleteTask(taskName);
    }

    /*@GetMapping(value = "/findByTaskName")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskData> getTaskDetailsByTaskName(@RequestParam final String taskName) {
        return taskManagerService.findTaskDetailsByTaskName(taskName);
    }

    @GetMapping(value = "/findByParentTaskName")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTaskDetailsByParentTaskName(@RequestParam final String parentTaskName) {
        return taskManagerService.findTaskDetailsByParentTaskName(parentTaskName);
    }*/

    @GetMapping(value = "/findTaskDetails")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskData> searchTaskDetails(@RequestParam final String taskName, @RequestParam final String parentTaskName,
                                            @RequestParam final Integer priorityFrom, @RequestParam final Integer priorityTo,
                                            @RequestParam final String startDate,
                                            @RequestParam final String endDate) {

        TaskDetail taskDetail = TaskDetail.builder()
                .task(StringUtils.isEmpty(taskName) ? null : taskName)
                .parentTask(StringUtils.isEmpty(parentTaskName) ? null : parentTaskName)
                .priorityFrom(priorityFrom)
                .priorityTo(priorityTo)
                .startDate(DateHandler.customDateFormatter(startDate))
                .endDate(DateHandler.customDateFormatter(endDate))
                .build();

        return taskManagerService.findTaskDetails(taskDetail);
    }
}
