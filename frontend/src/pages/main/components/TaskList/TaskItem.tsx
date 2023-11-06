import { TaskItemProps } from '@interfaces/main';

import { TaskBox } from '@main/MainStyle';

function TaskItem({ backgroundColor, projectname, membercount }: TaskItemProps) {
  return (
    <TaskBox backgroundColor={backgroundColor}>
      <p>{projectname}</p>
      <p>{membercount}</p>
      <p>{projectname}</p>
      <p>{projectname}</p>
      <p>{projectname}</p>
    </TaskBox>
  );
}

export default TaskItem;
