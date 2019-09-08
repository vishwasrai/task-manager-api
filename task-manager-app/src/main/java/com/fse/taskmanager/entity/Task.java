package com.fse.taskmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Task {

    @Id
    @Field("task_id")
    private String taskId;

    @Field("task_name")
    private String taskName;

    @Field("priority")
    private Integer priority;

    @Field("start_date")
    private String startDate;

    @Field("end_date")
    private String endDate;

    @DBRef
    private ParentTask parentTask;
}