import * as React from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import { Task } from '../../types/models';

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
  return (
    <div>
      <Modal
        open={props.isOpen}
        onClose={props.onClose}
      >
        <Box sx={style}>
          {props.task != undefined &&
            <>
              <Typography id="modal-modal-title" variant="h6" component="h2">
                {props.task.title}
              </Typography>
              <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                {props.task.description}
              </Typography>
              <Typography id="modal-modal-status" sx={{ mt: 2 }}>
                Status: {props.task.status}
              </Typography>
              <Typography id="modal-modal-category" sx={{ mt: 2 }}>
                Category: {props.task.category}
              </Typography>
              <Typography id="modal-modal-due-date" sx={{ mt: 2 }}>
                Due Date: {props.task.dueDate}
              </Typography>
            </>
          }
        </Box>
      </Modal>
    </div>
  );
}

export default TaskDetailsModal;
