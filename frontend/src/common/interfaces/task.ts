import { TaskData } from '@api/user';

export interface BoardItemProps extends TaskData {
  index: number;
  backgroundColor: string;
}
