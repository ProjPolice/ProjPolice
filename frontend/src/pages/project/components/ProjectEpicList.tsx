import { Container } from '@project/ProjectStyle';
import { ContainerNav } from '@project/ProjectStyle';
import { colors } from '@assets/design/colors';
import { EpicContainer } from '@project/ProjectStyle';
import { EpicItem } from '@project/ProjectStyle';
import { TaskInfoStyle } from '@project/ProjectStyle';

function ProjectEpicList() {
  const tasks = [
    {
      id: 1,
      name: '자료조사하기',
      startDate: 'SYYY.SM.SD',
      endDate: 'YYYY.MM.DD',
      status: 'TODO', // 'PROCEEDING', 'DONE'
      member: {
        id: 1,
        name: '김싸피',
        image: 'imageurl',
      },
      file: {
        name: '파일이름.format',
        uuid: 'UUID',
      },
    },
    {
      id: 2,
      name: '작업하기',
      startDate: 'SYYY.SM.SD',
      endDate: 'YYYY.MM.DD',
      status: 'PROCEEDING',
      member: {
        id: 2,
        name: '이싸피',
        image: 'imageurl',
      },
      file: {
        name: '파일이름.format',
        uuid: 'UUID',
      },
    },
  ];

  return (
    <Container width={'93%'} height={'90%'}>
      <ContainerNav height={'5%'} background={colors.yellow} />
      <EpicContainer width={'98%'} height={'85%'}>
        {tasks.map((task, index) => (
          <EpicItem key={index} width={'100%'} height={'20%'} background={colors.light}>
            <TaskInfoStyle>{task.name}</TaskInfoStyle>
            <TaskInfoStyle>할 일 (소속) </TaskInfoStyle>
            <TaskInfoStyle>{task.member.name}</TaskInfoStyle>
            <TaskInfoStyle>{task.endDate}</TaskInfoStyle>
            <TaskInfoStyle>{task.file.name}</TaskInfoStyle>
          </EpicItem>
        ))}
      </EpicContainer>
    </Container>
  );
}

export default ProjectEpicList;
