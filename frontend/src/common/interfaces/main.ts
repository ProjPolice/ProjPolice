import { Projects, TaskData } from '@api/user';

export interface HeaderBoxProps {
  count?: number;
  name?: string;
  icon?: string;
  backgroundColor?: string;
}

export interface ProjectItemProps extends Projects {
  index: number;
}

export interface TaskItemProps {
  items: TaskData[];
  backgroundColor: string;
}
