import React, { useState } from "react";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import Tooltip from "@mui/material/Tooltip";
import FilterListIcon from '@mui/icons-material/FilterList';
import Typography from "@mui/material/Typography";
import Checkbox from "@mui/material/Checkbox";
import FormGroup from "@mui/material/FormGroup";
import FormControlLabel from "@mui/material/FormControlLabel";

import Search from "../../search/Search";
import { fetchTasks } from "../../../api/taskApi";
import { Task, TaskStatus, TaskStatusString } from "../../../types/models";
import { mapTaskStatusToString } from "../../../utils/enumMapping";

import './EnhancedTableToolbar.scss';


interface EnhancedTableToolbarProps {
  setTasks: (tasks: Task[]) => void;
}

const EnhancedTableToolbar: React.FC<EnhancedTableToolbarProps> = (props) => {
  const [searchValue, setSearchValue] = useState<string>('');
  const [selectedStatus, setSelectedStatus] = useState<TaskStatus[]>(Object.values(TaskStatus));

  const searchTasks = async (searchValue: string) => {
    try {
      const tasks = await fetchTasks({
        title: searchValue,
        statuses: selectedStatus.map((status) => mapTaskStatusToString(status) as TaskStatusString)
      });
      props.setTasks(tasks);
    } catch (error) {
      console.error('Failed to fetch tasks');
    }
  };

  const handleStatusChange = (status: TaskStatus) => {
    if (selectedStatus.includes(status)) {
      if (selectedStatus.length === 1) {
        return;
      }
      setSelectedStatus(selectedStatus.filter((s) => s !== status));
    } else {
      setSelectedStatus([...selectedStatus, status]);
    }
  };

  return (
    <Toolbar
      sx={{
        pl: { sm: 2 },
        pr: { xs: 1, sm: 1 },
        display: 'flex',
        justifyContent: 'space-between',
        padding: '10px 0 0 0 !important',
      }}
    >
      <Typography variant="h6" id="tableTitle" component="div">
        Tasks
      </Typography>
      <div className="search-filter-wrap">
        <Search value={searchValue} onChange={setSearchValue}></Search>
        <FormGroup className="checkbox-wrap">
          {Object.values(TaskStatus).map((status) => (
            <FormControlLabel
              key={status}
              control={
                <Checkbox
                  checked={selectedStatus.includes(status)}
                  onChange={() => handleStatusChange(status)}
                />
              }
              label={status}
            />
          ))}
        </FormGroup>
        <Tooltip title="Filter list">
          <IconButton
            className="filter-button"
            onClick={() => searchTasks(searchValue)}
          >
            <FilterListIcon />
          </IconButton>
        </Tooltip>
      </div>
    </Toolbar>
  );
}

export default EnhancedTableToolbar;
