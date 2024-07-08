import React from 'react';
import Box from '@mui/material/Box';

import './TaskList.scss';
import { Task } from '../../types/models';

interface TaskListProps {
  tasks: Task[];
}

const TaskList: React.FC<TaskListProps> = (props) => {
  return (
    <Box className="task-list">
      {props.tasks.map((task) => (
        <div key={task.id} className="task-list-item">
          <h3>{task.title}</h3>
          <p>{task.description}</p>
        </div>
      ))}
    </Box>
  );
}

export default TaskList;