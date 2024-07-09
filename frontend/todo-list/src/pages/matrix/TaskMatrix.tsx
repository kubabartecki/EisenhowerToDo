import React, { useEffect, useState } from 'react';
import { Box, Grid, Typography, List, ListItem, Paper } from '@mui/material';

import { fetchTasks } from "../../api/taskApi";
import { Task, TaskCategory, TaskCategoryString } from '../../types/models';
import { mapTaskCategoryToString } from '../../utils/enumMapping';

import './TaskMatrix.scss';

interface EisenhowerMatrix {
  category: TaskCategory;
  tasks: Task[];
}

const TaskMatrix: React.FC = () => {
  const [matrix, setMatrix] = useState<EisenhowerMatrix[]>([]);

  const fetchTasksByCategory = async (category: TaskCategory): Promise<Task[]> => {
    try {
      const tasks = await fetchTasks({
        categories: [mapTaskCategoryToString(category) as TaskCategoryString],
      });
      return tasks;
    } catch (error) {
      console.error('Failed to fetch tasks');
      return [];
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      const newMatrix: EisenhowerMatrix[] = await Promise.all(
        Object.values(TaskCategory).map(async (category) => {
          const tasks = await fetchTasksByCategory(category);
          return { category, tasks: tasks || [] };
        })
      );
      setMatrix(newMatrix);
    };
    fetchData();
  }, []);

  return (
    <Box className="" sx={{ width: '100%', padding: '20px' }}>
      <Grid container spacing={2}>
        {matrix && matrix.map((field, index) => (
          <Grid item xs={6} key={index}>
            <Paper elevation={3} style={{ padding: '16px', minHeight: '250px'}}>
              <Typography variant="h6">{field.category}</Typography>
              <List>
                {field.tasks.map((task, idx) => (
                  <ListItem key={idx}>{task.title}</ListItem>
                ))}
              </List>
            </Paper>
          </Grid>
        ))}
      </Grid>
    </Box>
  );
}

export default TaskMatrix;
