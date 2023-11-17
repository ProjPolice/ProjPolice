import { BoardItemProps } from '@interfaces/task';

import { BoardBox } from '../TaskStyle';
import { Draggable } from 'react-beautiful-dnd';

function BoardItem({ id, name, startDate, endDate, epic, project, file, backgroundColor, index }: BoardItemProps) {
  return (
    <Draggable index={index} draggableId={`${id}`} key={id}>
      {(provided, snapshot) => (
        <div
          ref={provided.innerRef}
          {...provided.draggableProps}
          {...provided.dragHandleProps}
          style={{
            ...provided.draggableProps.style,
            width: snapshot.isDragging ? '270px' : '90%',
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
          }}
        >
          <BoardBox backgroundColor={backgroundColor} isDragging={snapshot.isDragging}>
            <div style={{ display: 'flex', justifyContent: 'space-between', paddingRight: '10%' }}>
              <p>{name}</p>
              {file ? <p>{file.name}</p> : <p>등록된 파일이 없습니다</p>}
            </div>
            <p>
              {startDate} ~ {endDate}
            </p>
            <p style={{ width: 'max-content', borderRadius: '5px' }}> {epic.name} </p>
            <p>{project.name}</p>
          </BoardBox>
        </div>
      )}
    </Draggable>
  );
}

export default BoardItem;
