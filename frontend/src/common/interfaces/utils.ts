import { Dispatch, SetStateAction } from 'react';
import { DropResult } from 'react-beautiful-dnd';
import { IBoardItems } from './task';

export interface MoveItemProps {
  result: DropResult;
  items: IBoardItems;
  setItems: Dispatch<SetStateAction<IBoardItems>>;
}
