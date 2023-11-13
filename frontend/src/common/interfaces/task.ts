import { TaskItem } from '@api/user';

export interface BoardItemProps extends TaskItem {
  index: number;
  backgroundColor: string;
}
