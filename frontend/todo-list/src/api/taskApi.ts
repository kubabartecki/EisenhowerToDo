import { get, post, put, del } from '../utils/request';
import { Task, TaskCreate, TaskUpdate } from '../types/models';

export const fetchTasks = (): Promise<Task[]> => {
  return get<Task[]>('/tasks/all');
};

export const fetchTaskById = (id: string): Promise<Task> => {
  return get<Task>(`/tasks/${id}`);
};

export const createTask = (task: TaskCreate): Promise<Task> => {
  return post<Task>('/tasks', task);
};

export const updateTask = (id: string, task: TaskUpdate): Promise<Task> => {
  return put<Task>(`/tasks/${id}`, task);
};

export const deleteTask = (id: string): Promise<void> => {
  return del<void>(`/tasks/${id}`);
};
