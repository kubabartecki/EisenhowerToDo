
export interface Task {
    id: number;
    title: string;
    description: string;
    status: TaskStatus;
    category: TaskCategory;
    dueDate: string;
}

export enum TaskStatus {
    TODO = "To Do",
    IN_PROGRESS = "In Progress",
    DONE = "Done"
}

export enum TaskCategory {
    DO = "Do",
    SCHEDULE = "Schedule",
    DELEGATE = "Delegate",
    ELIMINATE = "Eliminate"
}

export interface TaskCreate {
    title: string;
    description: string;
    isUrgent: boolean;
    isImportant: boolean;
    dueDate: string;
}

export interface TaskUpdate {
    title: string;
    description: string;
    isUrgent: boolean;
    isImportant: boolean;
    status: TaskStatus;
    dueDate: string;
}

