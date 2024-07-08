import React from 'react';
import Box from '@mui/material/Box';

import TaskPanel from '../../components/task-panel/TaskPanel';

import './HomePage.scss';


const HomePage: React.FC = () => {
  return (
    <Box className="home-page" sx={{ width: '100%' }}>
      <TaskPanel />
    </Box>
  );
}

export default HomePage;
