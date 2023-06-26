package com.example.gpthomework.repository;

import com.example.gpthomework.model.Task;
import com.example.gpthomework.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAll();

    Optional<Task> findById(Long id);

    Optional<List<Task>> findAllByTaskStatus(TaskStatus taskStatus);

}