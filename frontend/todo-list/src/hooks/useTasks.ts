import { useState, useEffect } from 'react';
import { fetchTasks } from '../api/taskApi';
import { Task } from '../types/models';

export const useTasks = () => {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const loadTasks = async () => {
      try {
        const data = await fetchTasks();
        setTasks(data);
      } catch (error) {
        setError('Failed to fetch tasks');
      } finally {
        setLoading(false);
      }
    };

    loadTasks();
  }, []);

  return { tasks, loading, error };
};
