import { colors } from '@assets/design/colors';
import { BoardContainer, Task, BoardSection, SectionContainer, TaskHeader, TaskBody, SectionTitle } from '../TaskStyle';
import BoardItem from './BoardItem';
import { DragDropContext, Droppable } from 'react-beautiful-dnd';
import { moveItem } from '@utils/moveItem';
import { useEffect, useState } from 'react';
import user from '@api/user';
import { IBoardItems } from '@interfaces/task';

function Board() {
  const [items, setItems] = useState<IBoardItems>({ TODO: [], PROCEEDING: [], DONE: [] });

  useEffect(() => {
    user.tasks().then((response) => {
      const todo = response.data.tasks.filter((t) => t.status === 'TODO');
      const proceeding = response.data.tasks.filter((p) => p.status === 'PROCEEDING');
      const done = response.data.tasks.filter((d) => d.status === 'DONE');
      console.log(todo);
      setItems({
        TODO: todo,
        PROCEEDING: proceeding,
        DONE: done,
      });
    });
  }, []);

  return (
    <BoardContainer height={'90%'} background={colors.white}>
      <Task>
        <TaskHeader />
        <TaskBody>
          <DragDropContext
            onDragEnd={(result) =>
              moveItem({
                result,
                items,
                setItems,
              })
            }
          >
            <SectionContainer>
              <SectionTitle>해야할 일</SectionTitle>
              <Droppable droppableId="TODO">
                {(provided) => (
                  <BoardSection backgroundColor={colors.board1} {...provided.droppableProps} ref={provided.innerRef}>
                    {items.TODO.map((todoItem, index) => (
                      <BoardItem {...todoItem} backgroundColor={colors.red} key={index} index={index} />
                    ))}
                    {provided.placeholder}
                  </BoardSection>
                )}
              </Droppable>
            </SectionContainer>
            <SectionContainer>
              <SectionTitle>진행 중인 일</SectionTitle>
              <Droppable droppableId="PROCEEDING">
                {(provided) => (
                  <BoardSection backgroundColor={colors.board2} {...provided.droppableProps} ref={provided.innerRef}>
                    {items.PROCEEDING.map((proceedingItem, index) => (
                      <BoardItem {...proceedingItem} backgroundColor={colors.yellow} key={index} index={index} />
                    ))}
                    {provided.placeholder}
                  </BoardSection>
                )}
              </Droppable>
            </SectionContainer>
            <SectionContainer>
              <SectionTitle>완료한 일</SectionTitle>
              <Droppable droppableId="DONE">
                {(provided) => (
                  <BoardSection backgroundColor={colors.board3} {...provided.droppableProps} ref={provided.innerRef}>
                    {items.DONE.map((doneItem, index) => (
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
