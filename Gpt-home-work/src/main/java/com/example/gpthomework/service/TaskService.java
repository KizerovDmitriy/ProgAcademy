package com.example.gpthomework.service;

import com.example.gpthomework.model.Task;
import com.example.gpthomework.model.TaskDTO;
import com.example.gpthomework.model.TaskStatus;
import com.example.gpthomework.repository.TaskRepository;
import com.example.gpthomework.service.mapper.TaskMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public List<Task> getAllTasks() {

        return taskRepository.findAll();
    }

    public Task createNewTask(TaskDTO taskDTO) {

        Task newTask = taskMapper.toEntity(taskDTO);
        newTask.setTaskStatus(TaskStatus.NEW);

        return taskRepository.save(newTask);
    }

    public Task markTaskIsComplete(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        task.setTaskStatus(TaskStatus.COMPLETE);

        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {

        Task taskToDelete = taskRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        taskRepository.delete(taskToDelete);
    }

    public Task updateTask(Long id, TaskDTO taskDTO) {

        Task updateTask = taskRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        updateTask.setTaskName(taskDTO.getTaskName());
        updateTask.setDescription(taskDTO.getDescription());
        updateTask.setTaskStatus(taskDTO.getTaskStatus());

        return taskRepository.save(updateTask);
    }

    public List<Task> filteredTasks(TaskStatus taskStatus) {

        return taskRepository.findAllByTaskStatus(taskStatus).orElseThrow(EntityNotFoundException::new);
    }

}