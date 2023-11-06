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
        <SectionHeader height={'8%'} background="" alignitems="end">
         <h3 style={{marginBottom:'1%'}}>프로젝트 상세</h3> 
        </SectionHeader>

        <ProjectSection height={'50%'} background="">
          <ProjectCalendarTimeline/>
          <ProjectDetail/>
        </ProjectSection>

        <SectionHeader height={'8%'} background="" alignitems="end">
         <h3 style={{marginBottom:'1%'}}>작업 목록</h3> 
        </SectionHeader>
        <ProjectSection height={'31%'} background="">
          <ProjectTaskList/>
        </ProjectSection>
      </CenterContainer>
    </Page>
  );
}

export default Project;
