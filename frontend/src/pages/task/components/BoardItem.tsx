import { BoardItemProps } from '@interfaces/task';

import { BoardBox } from '../TaskStyle';
import { Draggable } from 'react-beautiful-dnd';

function BoardItem({
  id,
  name,
  startDate,
  endDate,
  epic,
  project,
  file,
  userId,
  backgroundColor,
  index,
}: BoardItemProps) {
  return (
    <Draggable index={index} draggableId={`${id}`} key={id}>
      {(provided, snapshot) => (
        <div
          ref={provided.innerRef}
          {...provided.draggableProps}
          {...provided.dragHandleProps}
          style={{
            ...provided.draggableProps.style,
            width: snapshot.isDragging ? '300px' : '90%',
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
          }}
        >
          <BoardBox backgroundColor={backgroundColor} isDragging={snapshot.isDragging}>
            <p>{name}</p>
            <p>
              {startDate} ~ {endDate}
            </p>
            <p>{epic.name}</p>
            <p>{project.name}</p>
            <p>{file.name}</p>
            <p>{userId}</p>
          </BoardBox>
        </div>
      )}
    </Draggable>
  );
}

export default BoardItem;
