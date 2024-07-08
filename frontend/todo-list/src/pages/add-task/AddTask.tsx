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
import { TaskCreate } from '../../types/models';

import './AddTask.scss';

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

  const onSave = () => {
    console.log(task);
  }

  return (
    <div className='add-page'>
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
            console.log(event.target.value);
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
                setTask({ ...task, dueDate: newValue?.format() || "" });
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
    </div>
  );
}

export default AddTask;
