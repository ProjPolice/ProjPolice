import { ProjectItemListProps } from '@interfaces/main';

import { TaskBox } from '@main/MainStyle';


function TaskItem({ backgroundColor, projectname, membercount }: ProjectItemListProps) {
  return (
    <TaskBox backgroundColor={backgroundColor}>
      <div style={{ flex: 1 }}>
      </div>
      <div style={{ flex: 1, alignSelf : 'start' }}>
        <p>{projectname}</p>
      </div>
      <div style={{ flex: 2 }}>
        <p>{membercount}</p>
      </div>
      <div style={{ flex: 3 }}>
        <p>{projectname}</p>
      </div>
    </TaskBox>
  );
}


export default TaskItem;
