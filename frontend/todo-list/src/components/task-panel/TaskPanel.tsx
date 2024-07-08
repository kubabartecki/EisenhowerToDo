import React, { useState } from 'react';
import Box from '@mui/material/Box';

import TaskList from '../task-list/TaskList';
import { Task, TaskCategory, TaskStatus } from '../../types/models';

import './TaskPanel.scss';


const TaskPanel: React.FC = () => {
  const sampleTasks: Task[] = [
    {
      id: 1,
      title: 'Task 1',
      description: 'Description for Task 1',
      status: TaskStatus.TODO,
      category: TaskCategory.DO,
      dueDate: new Date(),
    },
    {
      id: 2,
      title: 'Task 2',
      description: 'Description for Task 2',
      status: TaskStatus.IN_PROGRESS,
      category: TaskCategory.SCHEDULE,
      dueDate: new Date(),
    },
    {
      id: 3,
      title: 'Task 3',
      description: 'Description for Task 3',
      status: TaskStatus.DONE,
      category: TaskCategory.DELEGATE,
      dueDate: new Date(),
    },
  ];
  const [tasks, setTasks] = useState(sampleTasks);

  return (
    <Box className="task-panel">
      <TaskList tasks={tasks}/>
    </Box>
  );
}

export default TaskPanel;
