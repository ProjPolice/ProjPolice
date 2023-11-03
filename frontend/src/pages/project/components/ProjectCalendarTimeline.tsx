import { Container } from '@project/ProjectStyle';
import { ContainerNav } from '@project/ProjectStyle';
import { ContainerContent } from '@project/ProjectStyle';

import ProjectCalendar from './ProjectCalendar';
import { colors } from '@assets/design/colors';

function ProjectCalendarTimeline() {
  return (
    <Container width={'60%'} height={'90%'} background={colors.white}>
      <ContainerNav height={'15%'} background="">
        <h3>프로젝트 이름</h3> <h3>버튼</h3>
      </ContainerNav>
      <ContainerNav height={'0.5%'} background={colors.default} />
      <ContainerContent height={'90%'}>
        <ProjectCalendar />
      </ContainerContent>
    </Container>
  );
}

export default ProjectCalendarTimeline;
