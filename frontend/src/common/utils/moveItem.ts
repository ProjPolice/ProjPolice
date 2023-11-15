import { AddItemProps, MoveItemProps } from '@interfaces/utils';

export const moveItem = ({
  result,
  todoItems,
  proceedingItems,
  doneItems,
  setTodoItems,
  setProceedingItems,
  setDoneItems,
}: MoveItemProps) => {
  const moved = deleteItem({
    result,
    todoItems,
    proceedingItems,
    doneItems,
    setTodoItems,
    setProceedingItems,
    setDoneItems,
  });
  addItem({
    result,
    todoItems,
    proceedingItems,
    doneItems,
    setTodoItems,
    setProceedingItems,
    setDoneItems,
    moved,
  });
};

const deleteItem = ({
  result,
  todoItems,
  proceedingItems,
  doneItems,
  setTodoItems,
  setProceedingItems,
  setDoneItems,
}: MoveItemProps) => {
  if (result.source.droppableId === 'todo') {
    const updatedItems = JSON.parse(JSON.stringify(todoItems));
    const movedItem = updatedItems.splice(result.source.index, 1);
    setTodoItems(updatedItems);
    return movedItem;
  } else if (result.source.droppableId === 'proceeding') {
    const updatedItems = JSON.parse(JSON.stringify(proceedingItems));
    const movedItem = updatedItems.splice(result.source.index, 1);
    setProceedingItems(updatedItems);
    return movedItem;
  } else {
    const updatedItems = JSON.parse(JSON.stringify(doneItems));
    const movedItem = updatedItems.splice(result.source.index, 1);
    setDoneItems(updatedItems);
    return movedItem;
  }
};

const addItem = ({
  result,
  todoItems,
  proceedingItems,
  doneItems,
  setTodoItems,
  setProceedingItems,
  setDoneItems,
  moved,
}: AddItemProps) => {
  if (result.destination) {
    if (result.destination.droppableId === 'todo') {
      const updatedItems = JSON.parse(JSON.stringify(todoItems));
      updatedItems.splice(result.destination.index, 0, moved);
      setTodoItems(updatedItems);
    } else if (result.destination.droppableId === 'proceeding') {
      const updatedItems = JSON.parse(JSON.stringify(proceedingItems));
      updatedItems.splice(result.destination.index, 0, moved);
      setProceedingItems(updatedItems);
    } else {
      const updatedItems = JSON.parse(JSON.stringify(doneItems));
      updatedItems.splice(result.destination.index, 0, moved);
      setDoneItems(updatedItems);
    }
  }
};
