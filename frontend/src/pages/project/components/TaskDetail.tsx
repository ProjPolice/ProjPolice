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
    projectName: '할 일 A',
    leader: '담당자 1',
    members: ['Member 1', 'Member 2', 'Member 3'],
    file: 'file1.pdf',
  },
  {
    projectName: '할 일 B',
    leader: '담당자 2',
    members: ['Member 4', 'Member 5', 'Member 6'],
    file: 'file2.pdf',
  },
];

function TaskDetail() {
  const selectedIndex = useRecoilValue(selectedIndexState);

  useEffect(() => {
    console.log(selectedIndex);
  }, [selectedIndex]);

  const selectedProject = projectDetails[selectedIndex];

  return (
    <Container width={'30%'} height={'90%'}>
      <ContainerNav height={'14.5%'} background="">
        <p>할 일 정보</p>
      </ContainerNav>
      <ContainerNav height={'1.5px'} background="#d8d8d8" />
      <DetailContainer width={'100%'} height={'85%'} background="" flexdirection="column">
        <DetailContainer width={'100%'} height={'30%'} background="" flexdirection="row">
          <DetailBox width={'50%'} height={'100%'} background="">
            <h6>{selectedProject?.projectName}</h6>
          </DetailBox>
          <DetailBox width={'50%'} height={'100%'} background="">
            <h6>{selectedProject?.leader}</h6>
          </DetailBox>
        </DetailContainer>
        <DetailBox width={'100%'} height={'40%'} background="">
          <h6>구성원</h6>
          <MemberList members={selectedProject?.members || []} />
        </DetailBox>
        <DetailBox width={'100%'} height={'40%'} background="">
          <h6>최신파일</h6>
          <h6>{selectedProject?.file}</h6>
        </DetailBox>
      </DetailContainer>
    </Container>
  );
}

export default TaskDetail;
