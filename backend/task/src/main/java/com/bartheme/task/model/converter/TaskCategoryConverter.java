package com.bartheme.task.model.converter;

import com.bartheme.task.model.TaskEisenhowerCategory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class TaskCategoryConverter implements AttributeConverter<TaskEisenhowerCategory, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TaskEisenhowerCategory category) {
        if (category == null) {
            return null;
        }
        return category.getValue();
    }

    @Override
    public TaskEisenhowerCategory convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        return Stream.of(TaskEisenhowerCategory.values())
                .filter(c -> c.getValue() == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}