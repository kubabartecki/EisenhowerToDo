import React from 'react';
import Box from '@mui/material/Box';
import LinearProgress from '@mui/material/LinearProgress';

import TaskList from '../task-list/TaskList';
import { useTasks } from '../../hooks/useTasks';


const TaskPanel: React.FC = () => {
  const { tasks, loading, error } = useTasks();
  if (error) return <p>{error}</p>;
  return (
    <Box className="task-panel">
      {loading ? <LinearProgress /> : <TaskList tasks={tasks}/>}
    </Box>
  );
}

export default TaskPanel;
