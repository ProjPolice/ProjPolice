import { TastkContainer } from '@main/MainStyle';

import TaskItem from '@main/components/TaskList/TaskItem';



function TaskList() {
  return <div>
    <TastkContainer height={'100%'} background="white">
      <TaskItem projectname={'프로젝트 1'} membercount={1} backgroundColor="#FED915"/>
      <TaskItem projectname={'프로젝트 2'} membercount={2} backgroundColor="#3056D3"/>
      <TaskItem projectname={'프로젝트 3'} membercount={3} backgroundColor="#000000"/>
    </TastkContainer>
  </div>;
}

export default TaskList;
