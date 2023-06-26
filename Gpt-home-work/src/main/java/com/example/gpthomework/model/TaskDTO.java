package com.example.gpthomework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class TaskDTO {

    @NotEmpty
    @NotNull
    private String taskName;
    @NotEmpty
    @NotNull
    private String description;
    @NotNull
    private TaskStatus taskStatus;
}
