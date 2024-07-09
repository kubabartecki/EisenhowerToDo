import { get, post, put, del } from '../utils/request';
import { Task, TaskCategoryString, TaskCreate, TaskStatusString, TaskUpdate } from '../types/models';
import { mapTaskCategory, mapTaskStatus } from '../utils/enumMapping';

export const fetchTasks = async (
  { statuses, categories, title, sortBy, sortDirection }: {
    statuses?: TaskStatusString[],
    categories?: TaskCategoryString[],
    title?: string,
    sortBy?: keyof Task,
    sortDirection?: 'asc' | 'desc'
  }
): Promise<Task[]> => {

  const params = [
    statuses ? `statuses=${statuses.join(',')}` : '',
    categories ? `categories=${categories.join(',')}` : '',
    title ? `title=${title}` : '',
    sortBy ? `sortBy=${sortBy}` : '',
    sortDirection ? `sortDirection=${sortDirection}` : '',
  ].filter(Boolean).join('&');

  const url = '/tasks/all' + (params ? `?${params}` : '');
  const tasks = await get<Task[]>(url);
  return tasks.map(task => ({
    ...task,
    status: mapTaskStatus(task.status),
    category: mapTaskCategory(task.category),
  }));
};

export const fetchTaskById = async (id: string): Promise<Task> => {
  const task = await get<Task>(`/tasks/${id}`);
  return {
    ...task,
    status: mapTaskStatus(task.status),
    category: mapTaskCategory(task.category),
  };
};

export const createTask = (task: TaskCreate): Promise<Task> => {
  return post<Task>('/tasks', task);
};

export const updateTask = (id: string, task: TaskUpdate): Promise<Task> => {
  return put<Task>(`/tasks/${id}`, task);
};

export const deleteTask = (id: string): Promise<string> => {
  return del<string>(`/tasks/${id}`);
};
