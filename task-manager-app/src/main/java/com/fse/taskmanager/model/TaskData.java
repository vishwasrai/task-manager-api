package com.fse.taskmanager.model;

import com.fse.taskmanager.entity.Task;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
@Builder
public class TaskData {

    private String taskId;

    private String task;

    private String parentTask;

    private Integer priority;

    private String startDate;

    private String endDate;

    public static TaskData buildTaskData(Task task) {
        return TaskData.builder()
                .taskId(task.getTaskId())
                .task(task.getTaskName())
                .parentTask(StringUtils.isEmpty(task.getParentTask()) ? "N/A" : task.getParentTask().getTask())
                .priority(task.getPriority())
                .startDate(task.getStartDate())
                .endDate(task.getEndDate())
                .build();
    }
}