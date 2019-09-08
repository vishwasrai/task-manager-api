/*
package com.fse.taskmanager.service;

import com.fse.taskmanager.entity.ParentTask;
import com.fse.taskmanager.entity.Task;
import com.fse.taskmanager.exception.ResourceNotFoundException;
import com.fse.taskmanager.model.TaskData;
import com.fse.taskmanager.model.TaskDetail;
import com.fse.taskmanager.repo.ParentTasksRepository;
import com.fse.taskmanager.repo.TasksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskManagerServiceTest {

    @InjectMocks
    private TaskManagerService taskManagerService;
    @Mock
    private TaskDetail taskDetail;
    @Mock
    private ParentTasksRepository parentTasksRepository;
    @Mock
    private TasksRepository tasksRepository;
    @Mock
    private Task taskData;

    private Task task;
    private TaskData taskDataResult;
    private ParentTask parentTask;

    @BeforeEach
    void setUp() {
        task = Task.builder()
                .taskId(1)
                .taskName("task")
                .parentTask(ParentTask.builder().task("parent").build())
                .priority(10)
                .startDate("20/03/2019")
                .endDate("30/04/2019")
                .build();
        taskDataResult = TaskData.builder()
                .task("task")
                .parentTask("parent")
                .priority(10)
                .startDate("20/03/2019")
                .endDate("30/04/2019")
                .build();
        parentTask = ParentTask.builder()
                .id(1)
                .task("task")
                .build();
    }

    @Test
    void testAddTaskDetails() {
        when(tasksRepository.findByTaskName(taskDetail.getTask())).thenReturn(Optional.empty());
        when(tasksRepository.save(any(Task.class))).thenReturn(task);
        Task task = taskManagerService.addTaskDetails(taskDetail);
        assertThat(task.getTaskId()).isEqualTo(1);
        assertThat(task.getTaskName()).isEqualTo("task");
        assertThat(task.getParentTask().getTask()).isEqualTo("parent");
        assertThat(task.getPriority()).isEqualTo(10);
        assertThat(task.getStartDate()).isEqualTo("20/03/2019");
        assertThat(task.getEndDate()).isEqualTo("30/04/2019");
    }

    @Test
    void testModifyTaskDetails() {
        when(tasksRepository.findById(anyInt())).thenReturn(Optional.of(task));
        when(tasksRepository.save(any(Task.class))).thenReturn(task);
        Task task = taskManagerService.modifyTaskDetails(anyInt(), taskDetail);
        assertNotNull(task);
        assertThat(task.getTaskId()).isEqualTo(1);
    }

    @Test
    void testDeleteTaskDetails() {
        when(tasksRepository.findByTaskName(anyString())).thenReturn(Optional.of(task));
        Task task = taskManagerService.deleteTask(anyString());
        assertThat(task.getTaskId()).isEqualTo(1);
        assertThat(task.getTaskName()).isEqualTo("task");
        assertThat(task.getParentTask().getTask()).isEqualTo("parent");
        assertThat(task.getPriority()).isEqualTo(10);
        assertThat(task.getStartDate()).isEqualTo("20/03/2019");
        assertThat(task.getEndDate()).isEqualTo("30/04/2019");
    }

    @Test
    void testFindTaskDetailsByTaskName() {
        when(tasksRepository.findByTaskName(anyString())).thenReturn(Optional.of(task));
        List<TaskData> taskDatas = taskManagerService.findTaskDetailsByTaskName(anyString());
        assertThat(taskDatas.size()).isEqualTo(1);
        taskDatas.stream().forEach(taskData -> {
            assertTrue(taskData.getTask().equals(taskDataResult.getTask()));
            assertTrue(taskData.getParentTask().equals(taskDataResult.getParentTask()));
            assertTrue(taskData.getPriority().equals(taskDataResult.getPriority()));
            assertTrue(taskData.getStartDate().equals(taskDataResult.getStartDate()));
            assertTrue(taskData.getEndDate().equals(taskDataResult.getEndDate()));
        });
    }

    @Test
    void testFindTaskDetailsByParentTaskName() {
        when(parentTasksRepository.findByTask(anyString())).thenReturn(Optional.of(parentTask));
        when(tasksRepository.findByParentTaskId(anyInt())).thenReturn(Arrays.asList(task));
        List<Task> taskDatas = taskManagerService.findTaskDetailsByParentTaskName(anyString());
        assertThat(taskDatas.size()).isEqualTo(1);
        taskDatas.stream().forEach(taskData -> {
            assertTrue(taskData.getTaskName().equals(taskDataResult.getTask()));
            assertTrue(taskData.getParentTask().getTask().equals(taskDataResult.getParentTask()));
            assertTrue(taskData.getPriority().equals(taskDataResult.getPriority()));
            assertTrue(taskData.getStartDate().equals(taskDataResult.getStartDate()));
            assertTrue(taskData.getEndDate().equals(taskDataResult.getEndDate()));
        });
    }

    @Test
    void testAddTaskDetails_withExistingTaskName() {
        when(tasksRepository.findByTaskName(taskDetail.getTask())).thenReturn(Optional.of(task));
        try {
            taskManagerService.addTaskDetails(taskDetail);
        } catch (ResourceNotFoundException exp) {
            assertThat(exp).hasMessage("Task name already exists.");
        }
    }

    @Test
    void testDeleteTaskDetails_withExistingTaskName() {
        when(tasksRepository.findByTaskName(anyString())).thenReturn(Optional.empty());
        try {
            taskManagerService.deleteTask(anyString());
        } catch (ResourceNotFoundException exp) {
            assertThat(exp).hasMessage("Task name does not exists.");
        }
    }
}*/
