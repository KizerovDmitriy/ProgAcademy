package com.example.gpthomework.service;

import com.example.gpthomework.model.Task;
import com.example.gpthomework.model.TaskStatus;
import com.example.gpthomework.repository.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    private List<Task> tasks;

    @BeforeEach
    void setUp() {

        tasks = new ArrayList<>();

        tasks.add(new Task(1L, "Task 1", "test", TaskStatus.NEW));
        tasks.add(new Task(2L, "Task 2", "test", TaskStatus.NEW));
        tasks.add(new Task(3L, "Task 3", "test", TaskStatus.NEW));

        when(taskRepository.findAll()).thenReturn(tasks);
    }

    @AfterEach
    void cleanTasks() {

        tasks.clear();
    }

    @Test
    void test_getAllTasks() {

        List<Task> result = taskRepository.findAll();

        assertEquals(tasks.size(), result.size());
        Assertions.assertTrue(tasks.containsAll(result));

        Mockito.verify(taskRepository).findAll();
    }

}