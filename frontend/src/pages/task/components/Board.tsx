import { colors } from '@assets/design/colors';
import { BoardContainer, Task, BoardSection, SectionContainer, TaskHeader, TaskBody, SectionTitle } from '../TaskStyle';
import BoardItem from './BoardItem';
import { DragDropContext, Droppable } from 'react-beautiful-dnd';
import { moveItem } from '@utils/moveItem';
import { useEffect, useState } from 'react';
import user, { TaskData } from '@api/user';

function Board() {
  const [todoItems, setTodoItems] = useState<TaskData[]>([]);
  const [proceedingItems, setProceedingItems] = useState<TaskData[]>([]);
  const [doneItems, setDoneItems] = useState<TaskData[]>([]);

  useEffect(() => {
    user.tasks().then((response) => {
      const todo = response.data.tasks.filter((t) => t.status === 'TODO');
      const proceeding = response.data.tasks.filter((p) => p.status === 'PROCEEDING');
      const done = response.data.tasks.filter((d) => d.status === 'DONE');
      setTodoItems(todo);
      setProceedingItems(proceeding);
      setDoneItems(done);
      console.log(todo);
      console.log(proceeding);
      console.log(done);
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
                todoItems,
                proceedingItems,
                doneItems,
                setTodoItems,
                setProceedingItems,
                setDoneItems,
              })
            }
          >
            <SectionContainer>
              <SectionTitle>해야할 일</SectionTitle>
              <Droppable droppableId="todo">
                {(provided) => (
                  <BoardSection backgroundColor={colors.board1} {...provided.droppableProps} ref={provided.innerRef}>
                    {todoItems.map((todoItem, index) => (
                      <BoardItem {...todoItem} backgroundColor={colors.red} key={index} index={index} />
                    ))}
                    {provided.placeholder}
                  </BoardSection>
                )}
              </Droppable>
            </SectionContainer>
            <SectionContainer>
              <SectionTitle>진행 중인 일</SectionTitle>
              <Droppable droppableId="proceeding">
                {(provided) => (
                  <BoardSection backgroundColor={colors.board2} {...provided.droppableProps} ref={provided.innerRef}>
                    {proceedingItems.map((proceedingItem, index) => (
                      <BoardItem {...proceedingItem} backgroundColor={colors.yellow} key={index} index={index} />
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
                    {doneItems.map((doneItem, index) => (
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
