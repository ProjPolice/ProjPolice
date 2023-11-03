import { colors } from '@assets/design/colors';
import { TastkContainer } from '@main/MainStyle';

import TaskItem from '@main/components/TaskList/TaskItem';

function TaskList() {
  return (
    <div>
      <TastkContainer height={'100%'} background={colors.white}>
        <TaskItem projectname={'프로젝트 1'} membercount={1} backgroundColor={colors.yellow} />
        <TaskItem projectname={'프로젝트 2'} membercount={2} backgroundColor={colors.blue} />
        <TaskItem projectname={'프로젝트 3'} membercount={3} backgroundColor={colors.black} />
      </TastkContainer>
    </div>
  );
}

export default TaskList;
