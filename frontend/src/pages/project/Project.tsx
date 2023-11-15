import { Page } from '@assets/design/globalStyles';
import { CenterContainer } from './ProjectStyle';
import { ProjectSection } from './ProjectStyle';
import { SectionHeader } from './ProjectStyle';
import ProjectCalendarTimeline from './components/ProjectCalendarTimeline';
import ProjectDetail from './components/ProjectDetail';
import ProjectTaskList from './components/ProjectTaskList';
import { currentProjectOwner, selectedIndexState } from 'state/project';
import { useRecoilValue } from 'recoil';
import { useParams } from 'react-router-dom';
import EpicDetail from './components/EpicDetail';
import ProjPoliceButton from '@widgets/buttons/ProjPoliceButton';
import { userIdState } from 'state/user';
import { useState } from 'react';
import AddMemberModal from '@widgets/modals/AddMemberModal';

function Project() {
  const selectedIndex = useRecoilValue(selectedIndexState);
  const projectOwner = useRecoilValue(currentProjectOwner);
  const userId = useRecoilValue(userIdState);

  const [visible, setVisible] = useState(false);

  const params = useParams();

  const projectId = Number(params.project_id);

  return (
    <Page>
      <AddMemberModal visible={visible} handleVisible={() => setVisible(!visible)} projectId={projectId} />
      <CenterContainer background="">
        <SectionHeader height={'8%'} background="" alignItems="center">
          <h3 style={{ marginRight: '2%' }}>프로젝트 상세</h3>
          {userId === projectOwner && (
            <ProjPoliceButton width={120} height={20} context="멤버 초대하기" onClick={() => setVisible(!visible)} />
          )}
        </SectionHeader>

        <ProjectSection height={'50%'} background="">
          <ProjectCalendarTimeline projectId={projectId} />
          {selectedIndex === -1 ? (
            <ProjectDetail projectId={projectId} />
          ) : (
            <EpicDetail projectId={projectId} epicId={selectedIndex} />
          )}
        </ProjectSection>

        <SectionHeader height={'8%'} background="" alignItems="end">
          <h3 style={{ marginBottom: '1%' }}>작업 목록</h3>
        </SectionHeader>
        <ProjectSection height={'31%'} background="">
          <ProjectTaskList projectId={projectId} epicId={selectedIndex} />
        </ProjectSection>
      </CenterContainer>
    </Page>
  );
}

export default Project;
