import { Container } from '@project/ProjectStyle';
import { ContainerNav } from '@project/ProjectStyle';
import { DetailContainer } from '@project/ProjectStyle';
import { DetailBox } from '@project/ProjectStyle';
import MemberList from './MemberList';
import { useRecoilValue } from 'recoil';
import { useEffect } from 'react';
import { selectedIndexState } from 'state/project';

const projectDetails = [
  {
    projectName: 'Project 1',
    leader: 'Project Leader 1',
    members: ['Member 1', 'Member 2', 'Member 3'],
  },
  {
    projectName: 'Project 2',
    leader: 'Project Leader 2',
    members: ['Member 4', 'Member 5', 'Member 6'],
  },
];

function TaskDetail() {
  const selectedIndex = useRecoilValue(selectedIndexState);

  useEffect(() => {
    console.log(selectedIndex);
  }, [selectedIndex]);

  // Conditional rendering based on the selectedIndex
  const selectedProject = projectDetails[selectedIndex];

  return (
    <Container width={'30%'} height={'90%'}>
      <ContainerNav height={'14.5%'} background="">
        <p>프로젝트 정보</p>
      </ContainerNav>
      <ContainerNav height={'1.5px'} background="#d8d8d8" />
      <DetailContainer width={'100%'} height={'85%'} background="" flexdirection="column">
        <DetailContainer width={'100%'} height={'30%'} background="" flexdirection="row">
          <DetailBox width={'50%'} height={'100%'} background="">
            <h6>프로젝트명</h6>
            <p>{selectedProject?.projectName}</p>
          </DetailBox>
          <DetailBox width={'50%'} height={'100%'} background="">
            <h6>담당자</h6>
            <p>{selectedProject?.leader}</p>
          </DetailBox>
        </DetailContainer>
        <DetailBox width={'100%'} height={'40%'} background="">
          <h6>구성원</h6>
          {/* Pass members to MemberList component */}
          <MemberList members={selectedProject?.members || []} />
        </DetailBox>
      </DetailContainer>
    </Container>
  );
}

export default TaskDetail;
