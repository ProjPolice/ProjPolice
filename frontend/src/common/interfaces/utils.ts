import { TaskData } from '@api/user';
import { Dispatch, SetStateAction } from 'react';
import { DropResult } from 'react-beautiful-dnd';

export interface MoveItemProps {
  result: DropResult;
  items: TaskData;
  setItems: Dispatch<SetStateAction<TaskData>>;
}
