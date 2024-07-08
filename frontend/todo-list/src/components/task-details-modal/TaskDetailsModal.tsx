import * as React from 'react';
import dayjs, { Dayjs } from 'dayjs';
import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import MenuItem from '@mui/material/MenuItem';
import { DemoContainer } from '@mui/x-date-pickers/internals/demo';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DateTimePicker } from '@mui/x-date-pickers/DateTimePicker';
import { Task, TaskCategory, TaskStatus } from '../../types/models';

const style = {
  position: 'absolute' as 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  borderRadius: '10px',
  boxShadow: 24,
  p: 4,
};

interface TaskDetailsModalProps {
  isOpen: boolean;
  task?: Task;
  onClose: () => void;
}

const TaskDetailsModal: React.FC<TaskDetailsModalProps> = (props) => {
  const [task, setTask] = React.useState<Task | undefined>(props.task);
  const [dueDate, setDueDate] = React.useState<Dayjs | null>(dayjs(props.task?.dueDate));
  
  React.useEffect
    (() => {
      setTask(props.task);
      setDueDate(dayjs(props.task?.dueDate));
    }, [props.task]);

  const onSave = () => {
    console.log("Todo save task");
    console.log(task);
    props.onClose();
  }

  return (
    <div>
      <Modal
        open={props.isOpen}
        onClose={props.onClose}
      >
        <Box sx={style}>
          {task != undefined &&
            <>
              <Box
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
                  id="status-select"
                  select
                  label="Status"
                  variant="standard"
                  value={task.status}
                  onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                    setTask({ ...task, status: event.target.value as TaskStatus });
                  }}
                >
                  {Object.values(TaskStatus).map((option) => (
                    <MenuItem key={option} value={option}>
                      {option}
                    </MenuItem>
                  ))}
                </TextField>
                <TextField
                  id="category-select"
                  select
                  label="Category"
                  variant="standard"
                  value={task.category}
                  onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                    setTask({ ...task, category: event.target.value as TaskCategory });
                  }}
                >
                  {Object.values(TaskCategory).map((option) => (
                    <MenuItem key={option} value={option}>
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
                        setTask({ ...task, dueDate: newValue?.format() || ""});
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
                Save
              </Button>
            </>
          }
        </Box>
      </Modal>
    </div>
  );
}

export default TaskDetailsModal;
