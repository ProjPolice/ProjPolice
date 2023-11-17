import { TaskData } from '@api/user';

export interface BoardItemProps extends TaskData {
  index: number;
  backgroundColor: string;
}

export interface IBoardItems {
  TODO: TaskData[];
  PROCEEDING: TaskData[];
  DONE: TaskData[];
}
