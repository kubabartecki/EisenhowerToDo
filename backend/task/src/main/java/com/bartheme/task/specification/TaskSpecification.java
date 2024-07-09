package com.bartheme.task.specification;

import com.bartheme.task.model.Task;
import com.bartheme.task.model.TaskEisenhowerCategory;
import com.bartheme.task.model.TaskStatus;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.stream.Collectors;

public class TaskSpecification implements Specification<Task> {

    private final List<String> statuses;
    private final List<String> categories;
    private final String title;

    public TaskSpecification(List<String> statuses, List<String> categories, String title) {
        this.statuses = statuses;
        this.categories = categories;
        this.title = title;
    }

    @Override
    public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();

        if (statuses != null && !statuses.isEmpty()) {
            List<TaskStatus> statusEnums = statuses.stream()
                    .map(TaskStatus::valueOf)
                    .collect(Collectors.toList());
            predicate = criteriaBuilder.and(predicate, root.get("status").in(statusEnums));
        }

        if (categories != null && !categories.isEmpty()) {
            List<TaskEisenhowerCategory> categoryEnums = categories.stream()
                    .map(TaskEisenhowerCategory::valueOf)
                    .collect(Collectors.toList());
            predicate = criteriaBuilder.and(predicate, root.get("category").in(categoryEnums));
        }

        if (title != null && !title.isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%"
            ));
        }

        return predicate;
    }
}
