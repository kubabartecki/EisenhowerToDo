// src/theme.js
import { createTheme } from '@mui/material/styles';

const theme = createTheme({
  palette: {
    primary: {
      main: '#0b4f6c', // Indigo Dye
    },
    secondary: {
      main: '#145c9e', // Lapis Lazuli
    },
    error: {
      main: '#c62828',
    },
    warning: {
      main: '#ff9800',
    },
    info: {
      main: '#2196f3',
    },
    success: {
      main: '#4caf50',
    },
    background: {
      default: '#cbb9a8', // Dun
      paper: '#dcc7be', // Pale Dogwood
    },
    text: {
      primary: '#1f271b', // Black Olive
      secondary: '#0b4f6c', // Indigo Dye
    },
  },
  typography: {
    fontFamily: 'Roboto, Arial, sans-serif',
  },
});

export default theme;
