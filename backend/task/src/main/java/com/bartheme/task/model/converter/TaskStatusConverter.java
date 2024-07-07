package com.bartheme.task.model.converter;

import com.bartheme.task.model.TaskStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;


@Converter(autoApply = true)
public class TaskStatusConverter implements AttributeConverter<TaskStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TaskStatus status) {
        if (status == null) {
            return null;
        }
        return status.getValue();
    }

    @Override
    public TaskStatus convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        return Stream.of(TaskStatus.values())
                .filter(c -> c.getValue() == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
