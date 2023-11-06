import { Container } from '@project/ProjectStyle';
import { ContainerNav } from '@project/ProjectStyle';
import { ContainerContent } from '@project/ProjectStyle';

import ProjectCalendar from './ProjectCalendar';
import { colors } from '@assets/design/colors';

function ProjectCalendarTimeline() {
  return (
    <Container width={"60%"} height={'90%'}>
      <ContainerNav height={'15%'} background="">
        <p>프로젝트 이름</p> <p>버튼</p>
      </ContainerNav>
      <ContainerNav height={'0.5%'} background="#d8d8d8"/>
      <ContainerContent height={'90%'} background="">
        <ProjectCalendar/>
      </ContainerContent>
    </Container>
  );
}

export default ProjectCalendarTimeline;
