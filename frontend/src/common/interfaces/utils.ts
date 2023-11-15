import { TaskData } from '@api/user';
import { Dispatch, SetStateAction } from 'react';
import { DropResult } from 'react-beautiful-dnd';

export interface MoveItemProps {
  result: DropResult;
  todoItems: TaskData[];
  setTodoItems: Dispatch<SetStateAction<TaskData[]>>;
  proceedingItems: TaskData[];
  setProceedingItems: Dispatch<SetStateAction<TaskData[]>>;
  doneItems: TaskData[];
  setDoneItems: Dispatch<SetStateAction<TaskData[]>>;
}

export interface AddItemProps extends MoveItemProps {
  moved: TaskData;
}
