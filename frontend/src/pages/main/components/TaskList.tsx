import { colors } from '@assets/design/colors';
import { TaskBoxContainer, TaskContainer, TextContainer } from '@main/MainStyle';

import TaskItem from '@main/components/TaskList/TaskItem';

function TaskList() {
  return (
    <TaskContainer>
      <TextContainer>
        <h4>임박한 작업</h4>
        <p>전체보기</p>
      </TextContainer>
      <TaskBoxContainer>
        <TaskItem projectname={'프로젝트 1'} membercount={1} backgroundColor={colors.yellow} />
        <TaskItem projectname={'프로젝트 2'} membercount={2} backgroundColor={colors.blue} />
        <TaskItem projectname={'프로젝트 3'} membercount={3} backgroundColor={colors.black} />
      </TaskBoxContainer>
    </TaskContainer>
  );
}

export default TaskList;
