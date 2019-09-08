package com.fse.taskmanager.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fse.taskmanager.util.DateHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDetail {

    private String task;

    private int priority;

    private Integer priorityFrom;

    private Integer priorityTo;

    private String parentTask;

    private String startDate;

    private String endDate;
}