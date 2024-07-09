import React, { useState } from "react";
import Toolbar from "@mui/material/Toolbar";
import Search from "../../search/Search";
import IconButton from "@mui/material/IconButton";
import Tooltip from "@mui/material/Tooltip";
import FilterListIcon from '@mui/icons-material/FilterList';
import Typography from "@mui/material/Typography";

import { fetchTasks } from "../../../api/taskApi";
import { Task } from "../../../types/models";

import './EnhancedTableToolbar.scss';

interface EnhancedTableToolbarProps {
  setTasks: (tasks: Task[]) => void;
}

const EnhancedTableToolbar: React.FC<EnhancedTableToolbarProps> = (props) => {
  const [searchValue, setSearchValue] = useState<string>('');

  const searchTasks = async (searchValue: string) => {
    if (!searchValue) return;
    try {
      const tasks = await fetchTasks({title: searchValue});
      props.setTasks(tasks);
    } catch (error) {
      console.error('Failed to fetch tasks');
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
      <Typography
        variant="h6"
        id="tableTitle"
        component="div"
      >
        Tasks
      </Typography>
      <div className="search-filter-wrap">
        <Search
          value={searchValue}
          onChange={setSearchValue}
        ></Search>
        <Tooltip title="Filter list" className="filter-button-tooltip">
          <IconButton className="filter-button"
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
