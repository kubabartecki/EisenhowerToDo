import { get, post, put, del } from '../utils/request';
import { Task } from '../types/models';

export const fetchTasks = (): Promise<Task[]> => {
  return get<Task[]>('/tasks/all');
};

export const fetchTaskById = (id: string): Promise<Task> => {
  return get<Task>(`/tasks/${id}`);
};

export const createTask = (task: Task): Promise<Task> => {
  return post<Task>('/tasks', task);
};

export const updateTask = (id: string, task: Task): Promise<Task> => {
  return put<Task>(`/tasks/${id}`, task);
};

export const deleteTask = (id: string): Promise<void> => {
  return del<void>(`/tasks/${id}`);
};
