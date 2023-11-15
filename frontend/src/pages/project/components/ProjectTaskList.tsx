import { Container } from '@project/ProjectStyle';
import { ContainerNav } from '@project/ProjectStyle';
import { colors } from '@assets/design/colors';
import { EpicContainer } from '@project/ProjectStyle';
import { EpicItem } from '@project/ProjectStyle';
import { TaskInfoStyle } from '@project/ProjectStyle';

import task, { TasksData } from '@api/task';
import { useEffect, useState } from 'react';
import { EpicDetailProps } from '@interfaces/project';

function ProjectTaskList({ projectId, epicId }: EpicDetailProps) {
  const [tasks, setTasks] = useState<TasksData[]>([]);

  console.log(epicId, '여기');

  useEffect(() => {
    task
      .data(projectId, epicId)
      .then((response) => {
        setTasks(response.data);
        console.log(response.data, '여기');
      })
      .catch((error) => {
        console.log(error);
      });
  }, [projectId, epicId]);

  return (
    <Container width={'93%'} height={'90%'}>
      <ContainerNav height={'5%'} background={colors.yellow} />
      <EpicContainer width={'98%'} height={'85%'}>
        {tasks.map((task, index) => (
          <EpicItem key={index} width={'100%'} height={'20%'} background={colors.light}>
            <TaskInfoStyle>{task.name}</TaskInfoStyle>
            <TaskInfoStyle>할 일 (소속) </TaskInfoStyle>
            <TaskInfoStyle>{task.name}</TaskInfoStyle>
            <TaskInfoStyle>{task.endDate}</TaskInfoStyle>
            <TaskInfoStyle>{}</TaskInfoStyle>
          </EpicItem>
        ))}
      </EpicContainer>
    </Container>
  );
}

export default ProjectTaskList;
