import { Page } from '@assets/design/globalStyles';
import { CenterContainer } from './ProjectStyle';
import { ProjectSection } from './ProjectStyle';

import { SectionHeader } from './ProjectStyle';
import ProjectCalendarTimeline from './components/ProjectCalendarTimeline';
import ProjectDetail from './components/ProjectDetail';
import ProjectTaskList from './components/ProjectTaskList';

function Project() {
  return (
    <Page>
      <CenterContainer background="">
        <SectionHeader height={'10%'} background="" alignitems="center">
          <h2 style={{ marginTop: '5%' }}>프로젝트 상세</h2>
        </SectionHeader>

        <ProjectSection height={'47%'} background="">
          <ProjectCalendarTimeline />
          <ProjectDetail />
        </ProjectSection>

        <SectionHeader height={'6%'} background="" alignitems="bottom">
          <h2>작업 목록</h2>
        </SectionHeader>
        <ProjectSection height={'35%'} background="">
          <ProjectTaskList />
        </ProjectSection>
      </CenterContainer>
    </Page>
  );
}

export default Project;
