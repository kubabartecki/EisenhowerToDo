import * as React from 'react';
import { Dayjs } from 'dayjs';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import MenuItem from '@mui/material/MenuItem';
import { DemoContainer } from '@mui/x-date-pickers/internals/demo';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DateTimePicker } from '@mui/x-date-pickers/DateTimePicker';
import LinearProgress from '@mui/material/LinearProgress';

import { TaskCreate } from '../../types/models';
import { createTask } from '../../api/taskApi';

import './AddTask.scss';
import Snackbar from '@mui/material/Snackbar';
import Alert from '@mui/material/Alert';

const initialTask: TaskCreate = {
  title: "",
  description: "",
  isUrgent: false,
  isImportant: false,
  dueDate: ""
};
const booleanOptions = ["True", "False"];

const AddTask: React.FC = () => {
  const [task, setTask] = React.useState<TaskCreate>(initialTask);
  const [dueDate, setDueDate] = React.useState<Dayjs | null>(null);
  const [loading, setLoading] = React.useState<boolean>(false);
  const [showSnackbar, setShowSnackbar] = React.useState<boolean>(false);
  const [showErrorSnackbar, setShowErrorSnackbar] = React.useState<boolean>(false);

  const onSave = () => {
    saveTask(task);
  }
  const saveTask = async (task: TaskCreate) => {
    try {
      await createTask(task);
      setShowSnackbar(true);
    } catch (error) {
      console.error('Failed to create task');
      setShowErrorSnackbar(true);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className='add-page'>
      {loading && <LinearProgress />}
      <h2 className='add-task-header'>Create new task</h2>
      <Box
        className='add-task-form'
        component="form"
        sx={{
          '& > :not(style)': { m: 1, width: '35ch' },
        }}
        noValidate
        autoComplete="off"
      >
        <TextField
          id="title-input"
          label="Title"
          variant="standard"
          required
          value={task.title}
          onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
            setTask({ ...task, title: event.target.value });
          }}
        />
        <TextField
          id="description-input"
          label="Description"
          variant="standard"
          value={task.description}
          onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
            setTask({ ...task, description: event.target.value });
          }}
          multiline
        />
        <TextField
          id="is-urgent-select"
          select
          label="Is Urgent"
          variant="standard"
          required
          value={task.isUrgent ? "True" : "False"}
          onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
            setTask({ ...task, isUrgent: event.target.value == "True" });
          }}
        >
          {booleanOptions.map((option, index) => (
            <MenuItem key={index} value={option}>
              {option}
            </MenuItem>
          ))}
        </TextField>
        <TextField
          id="is-important-select"
          select
          label="Is Important"
          variant="standard"
          required
          value={task.isImportant ? "True" : "False"}
          onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
            setTask({ ...task, isImportant: event.target.value == "True" });
          }}
        >
          {booleanOptions.map((option, index) => (
            <MenuItem key={index} value={option}>
              {option}
            </MenuItem>
          ))}
        </TextField>
        <LocalizationProvider dateAdapter={AdapterDayjs}>
          <DemoContainer components={['DateTimePicker']}>
            <DateTimePicker
              label="Due Date"
              value={dueDate}
              onChange={(newValue) => {
                setDueDate(newValue);
                setTask({ ...task, dueDate: newValue?.format('YYYY-MM-DDTHH:mm:ss') || "" });
              }}
            />
          </DemoContainer>
        </LocalizationProvider>
      </Box>
      <Button
        sx={{ mt: 2 }}
        variant="contained"
        onClick={onSave}
      >
        Create
      </Button>
      <Snackbar
        open={showSnackbar}
        autoHideDuration={3000}
        onClose={(event: React.SyntheticEvent | Event, reason?: string) => {
          if (reason === 'clickaway') {
            return;
          }
          setShowSnackbar(false);
        }}
      >
        <Alert
          severity="success"
          variant="filled"
          sx={{ width: '100%' }}
        >
          Task created successfully!
        </Alert>
      </Snackbar>
      <Snackbar
        open={showErrorSnackbar}
        autoHideDuration={3000}
        onClose={(event: React.SyntheticEvent | Event, reason?: string) => {
          if (reason === 'clickaway') {
            return;
          }
          setShowErrorSnackbar(false);
        }}
      >
        <Alert
          severity="error"
          variant="filled"
          sx={{ width: '100%' }}
        >
          Error while creating task!
        </Alert>
      </Snackbar>
    </div>
  );
}

export default AddTask;
