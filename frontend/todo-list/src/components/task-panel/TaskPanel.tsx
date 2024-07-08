import React from 'react';
import Box from '@mui/material/Box';
import LinearProgress from '@mui/material/LinearProgress';

import TaskList from '../task-list/TaskList';
import { useTasks } from '../../hooks/useTasks';


const TaskPanel: React.FC = () => {
  const { tasks, loading, error, setShouldRefresh } = useTasks();
  if (error) return <p>{error}</p>;
  console.log(tasks);
  return (
    <Box className="task-panel">
      {
        loading ?
          <LinearProgress /> :
          <TaskList tasks={tasks} onRefresh={() => setShouldRefresh(true)}/>
      }
    </Box>
  );
}

export default TaskPanel;
