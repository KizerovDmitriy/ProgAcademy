package com.example.gpthomework.controller;

import com.example.gpthomework.model.Task;
import com.example.gpthomework.model.TaskDTO;
import com.example.gpthomework.model.TaskStatus;
import com.example.gpthomework.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){

        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping
    public ResponseEntity<Task> createNewTask(@RequestBody TaskDTO taskDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createNewTask(taskDTO));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Task> changeStatusToComplete(@PathVariable Long id) {

        return ResponseEntity.ok(taskService.markTaskIsComplete(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {

        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,@RequestBody TaskDTO taskDTO) {

        return ResponseEntity.ok(taskService.updateTask(id,taskDTO));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Task>> filteredTasks(@RequestParam("status") TaskStatus taskStatus){

        return ResponseEntity.ok(taskService.filteredTasks(taskStatus));
    }

}