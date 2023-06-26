package com.example.gpthomework.service.mapper;

import com.example.gpthomework.model.Task;
import com.example.gpthomework.model.TaskDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toEntity(TaskDTO taskDTO);

    TaskDTO toDto(Task task);

}
