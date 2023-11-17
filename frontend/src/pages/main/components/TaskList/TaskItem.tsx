import styled from '@emotion/styled';
import { TaskItemProps } from '@interfaces/main';

import { TaskBox, TaskListBody, TaskListHeader, TaskListItem } from '@main/MainStyle';
import { parseStatus } from '@utils/parseStatus';

function TaskItem({ backgroundColor, items }: TaskItemProps) {
  return (
    <>
      <TaskBox backgroundColor={backgroundColor}>
        <TaskListHeader>
          <Text>작업</Text>
          <Text>할 일</Text>
          <Text>프로젝트</Text>
          <Text>진행 현황</Text>
          <Text>완료일</Text>
        </TaskListHeader>
        <TaskListBody>
          {items.map((item, index) => (
            <TaskListItem key={index}>
              <Text>{item.name}</Text>
              <Text>{item.epic.name}</Text>
              <Text>{item.project.name}</Text>
              <Text>{parseStatus(item.status)}</Text>
              <Text>{item.endDate}</Text>
            </TaskListItem>
          ))}
        </TaskListBody>
      </TaskBox>
    </>
  );
}

export default TaskItem;

const Text = styled.p`
  width: 15%;
  text-align: center;
`;
