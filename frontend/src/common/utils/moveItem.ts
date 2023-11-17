import task from '@api/task';
import { MoveItemProps } from '@interfaces/utils';

export const moveItem = ({ result, items, setItems }: MoveItemProps) => {
  const updatedItems = JSON.parse(JSON.stringify(items));
  if (result.destination) {
    task
      .modify(updatedItems[result.source.droppableId][result.source.index].id, {
        status: result.destination.droppableId,
      })
      .then(() => {
        const [moved] = updatedItems[result.source.droppableId].splice(result.source.index, 1);
        const updated = JSON.parse(JSON.stringify(moved));
        if (result.destination) {
          updated.status = result.destination.droppableId;
          updatedItems[result.destination.droppableId].splice(result.destination.index, 0, moved);
        }
        setItems(updatedItems);
      })
      .catch((error) => {
        console.log(error);
      });
  }
};
