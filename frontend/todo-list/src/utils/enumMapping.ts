import { TaskStatus, TaskCategory, TaskStatusString, TaskCategoryString } from '../types/models';

export const mapTaskStatus = (status: string): TaskStatus => {
    switch (status) {
        case 'TODO':
            return TaskStatus.TODO;
        case 'IN_PROGRESS':
            return TaskStatus.IN_PROGRESS;
        case 'DONE':
            return TaskStatus.DONE;
        default:
            throw new Error(`Unknown status: ${status}`);
    }
};

export const mapTaskCategory = (category: string): TaskCategory => {
    switch (category) {
        case 'DO':
            return TaskCategory.DO;
        case 'SCHEDULE':
            return TaskCategory.SCHEDULE;
        case 'DELEGATE':
            return TaskCategory.DELEGATE;
        case 'ELIMINATE':
            return TaskCategory.ELIMINATE;
        default:
            throw new Error(`Unknown status: ${category}`);
    }
};

export const mapTaskStatusToString = (status: TaskStatus): TaskStatusString => {
    switch (status) {
        case TaskStatus.TODO:
            return 'TODO';
        case TaskStatus.IN_PROGRESS:
            return 'IN_PROGRESS';
        case TaskStatus.DONE:
            return 'DONE';
        default:
            throw new Error(`Unknown status: ${status}`);
    }
};

export const mapTaskCategoryToString = (category: TaskCategory): TaskCategoryString => {
    switch (category) {
        case TaskCategory.DO:
            return 'DO';
        case TaskCategory.SCHEDULE:
            return 'SCHEDULE';
        case TaskCategory.DELEGATE:
            return 'DELEGATE';
        case TaskCategory.ELIMINATE:
            return 'ELIMINATE';
        default:
            throw new Error(`Unknown status: ${category}`);
    }
};
