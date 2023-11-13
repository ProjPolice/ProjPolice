import { MoveItemProps } from '@interfaces/utils';

export const moveItem = ({ result, items, setItems }: MoveItemProps) => {
  const updatedItems = JSON.parse(JSON.stringify(items));
  if (result.destination) {
    const prev = result.source.droppableId;
    const next = result.destination.droppableId;
    const prevIndex = result.source.index;
    const nextIndex = result.destination.index;
    const movedItem = updatedItems[prev].splice(prevIndex, 1)[0];
    updatedItems[next].splice(nextIndex, 0, movedItem);
  }
  console.log(updatedItems);
  setItems(updatedItems);
};
