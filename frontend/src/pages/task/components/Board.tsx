import { colors } from '@assets/design/colors';
import { BoardContainer, Task, BoardSection, SectionContainer, TaskHeader, TaskBody, SectionTitle } from '../TaskStyle';
import BoardItem from './BoardItem';
import { DragDropContext, Droppable } from 'react-beautiful-dnd';
import { moveItem } from '@utils/moveItem';
import { useState } from 'react';

function Board() {
  const dummyitems = {
    todo: [
      {
        id: 0,
        name: 'PPT1',
        startDate: '2023-11-13',
        endDate: '2023-11-20',
        epic: {
          id: 0,
          name: '발표',
        },
        project: {
          id: 0,
          name: '프로폴리스',
        },
        file: {
          id: 0,
          name: '발표자료',
        },
        userId: 0,
      },
      {
        id: 1,
        name: 'PPT2',
        startDate: '2023-11-13',
        endDate: '2023-11-20',
        epic: {
          id: 0,
          name: '발표',
        },
        project: {
          id: 0,
          name: '프로폴리스',
        },
        file: {
          id: 0,
          name: '발표자료',
        },
        userId: 0,
      },
    ],
    inProgress: [
      {
        id: 2,
        name: 'PPT3',
        startDate: '2023-11-13',
        endDate: '2023-11-20',
        epic: {
          id: 0,
          name: '발표',
        },
        project: {
          id: 0,
          name: '프로폴리스',
        },
        file: {
          id: 0,
          name: '발표자료',
        },
        userId: 0,
      },
      {
        id: 3,
        name: 'PPT4',
        startDate: '2023-11-13',
        endDate: '2023-11-20',
        epic: {
          id: 0,
          name: '발표',
        },
        project: {
          id: 0,
          name: '프로폴리스',
        },
        file: {
          id: 0,
          name: '발표자료',
        },
        userId: 0,
      },
    ],
    done: [
      {
        id: 4,
        name: 'PPT5',
        startDate: '2023-11-13',
        endDate: '2023-11-20',
        epic: {
          id: 0,
          name: '발표',
        },
        project: {
          id: 0,
          name: '프로폴리스',
        },
        file: {
          id: 0,
          name: '발표자료',
        },
        userId: 0,
      },
      {
        id: 5,
        name: 'PPT6',
        startDate: '2023-11-13',
        endDate: '2023-11-20',
        epic: {
          id: 0,
          name: '발표',
        },
        project: {
          id: 0,
          name: '프로폴리스',
        },
        file: {
          id: 0,
          name: '발표자료',
        },
        userId: 0,
      },
    ],
  };
  const [items, setItems] = useState(dummyitems);
  return (
    <BoardContainer height={'90%'} background={colors.white}>
      <Task>
        <TaskHeader />
        <TaskBody>
          <DragDropContext onDragEnd={(result) => moveItem({ result, items, setItems })}>
            <SectionContainer>
              <SectionTitle>해야할 일</SectionTitle>
              <Droppable droppableId="todo">
                {(provided) => (
                  <BoardSection backgroundColor={colors.board1} {...provided.droppableProps} ref={provided.innerRef}>
                    {items.todo.map((todoItem, index) => (
                      <BoardItem {...todoItem} backgroundColor={colors.red} key={index} index={index} />
                    ))}
                    {provided.placeholder}
                  </BoardSection>
                )}
              </Droppable>
            </SectionContainer>
            <SectionContainer>
              <SectionTitle>진행 중인 일</SectionTitle>
              <Droppable droppableId="inProgress">
                {(provided) => (
                  <BoardSection backgroundColor={colors.board2} {...provided.droppableProps} ref={provided.innerRef}>
                    {items.inProgress.map((inProgressItem, index) => (
                      <BoardItem {...inProgressItem} backgroundColor={colors.yellow} key={index} index={index} />
                    ))}
                    {provided.placeholder}
                  </BoardSection>
                )}
              </Droppable>
            </SectionContainer>
            <SectionContainer>
              <SectionTitle>완료한 일</SectionTitle>
              <Droppable droppableId="done">
                {(provided) => (
                  <BoardSection backgroundColor={colors.board3} {...provided.droppableProps} ref={provided.innerRef}>
                    {items.done.map((doneItem, index) => (
                      <BoardItem {...doneItem} backgroundColor={colors.green} key={index} index={index} />
                    ))}
                    {provided.placeholder}
                  </BoardSection>
                )}
              </Droppable>
            </SectionContainer>
          </DragDropContext>
        </TaskBody>
      </Task>
    </BoardContainer>
  );
}

export default Board;
