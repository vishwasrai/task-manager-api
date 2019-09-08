package com.fse.taskmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParentTask {

    @Id
    @Field
    private String id;

    @Field("parent_task")
    private String task;

}
