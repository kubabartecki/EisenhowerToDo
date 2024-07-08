import { Routes, Route, Link } from "react-router-dom";
import { ThemeProvider } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';

import NavigationBar from './components/navbar/NavigationBar';
import HomePage from './pages/home/HomePage';
import AddTask from "./pages/add-task/AddTask";
import TaskMatrix from "./pages/matrix/TaskMatrix";

import theme from './theme/theme';
import './App.css';
import "./theme/variables.css";
import "./theme/global.scss";

const App: React.FC = () => {
  return (
    <div className="App">
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <NavigationBar></NavigationBar>
        <Routes>
          <Route index element={<HomePage />} />
          <Route path="add" element={<AddTask />} />
          <Route path="matrix" element={<TaskMatrix />} />
          <Route path="*" element={<NoMatch />} />
        </Routes>
      </ThemeProvider>
    </div>
  );
}

function NoMatch() {
  return (
    <div>
      <h2>Nothing to see here!</h2>
      <p>
        <Link to="/">Go to the home page</Link>
      </p>
    </div>
  );
}

export default App;
