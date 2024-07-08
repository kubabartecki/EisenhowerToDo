import React, { useState } from 'react';
import Box from '@mui/material/Box';

import TaskList from '../task-list/TaskList';
import { Task, TaskCategory, TaskStatus } from '../../types/models';
import { useTasks } from '../../hooks/useTasks';


const TaskPanel: React.FC = () => {
  const { tasks, loading, error } = useTasks();
  if (loading) return <p>Loading...</p>;
  if (error) return <p>{error}</p>;
  return (
    <Box className="task-panel">
      <TaskList tasks={tasks}/>
    </Box>
  );
}

export default TaskPanel;
