import { Container } from '@project/ProjectStyle';
import { ContainerNav } from '@project/ProjectStyle';
import { DetailContainer } from '@project/ProjectStyle';
import { DetailBox } from '@project/ProjectStyle';
import MemberList from './MemberList';
import { useRecoilState, useRecoilValue } from 'recoil';
import { projectDataState, projectMemberState } from 'state/project';
import { useEffect } from 'react';
import project from '@api/project';
import { ProjectIdProps } from '@interfaces/project';

function ProjectDetail({ projectId }: ProjectIdProps) {
  const item = useRecoilValue(projectDataState);
  const [members, setMembers] = useRecoilState(projectMemberState);

  useEffect(() => {
    project
      .memberdata(projectId)
      .then((response) => {
        console.log(response.data.members);
        setMembers(response.data.members);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);
  return (
    <Container width={'30%'} height={'90%'}>
      <ContainerNav height={'14.5%'} background="">
        <p>프로젝트 정보</p>
      </ContainerNav>
      <ContainerNav height={'1.5px'} background="#d8d8d8" />
      <DetailContainer width={'100%'} height={'85%'} background="" flexdirection="column">
        <DetailContainer width={'100%'} height={'30%'} background="" flexdirection="row">
          <DetailBox width={'50%'} height={'100%'} background="">
            <h6>프로젝트명</h6> <p>{item.name}</p>
          </DetailBox>
          <DetailBox width={'50%'} height={'100%'} background="">
            <h6>담당자</h6> <p>{item.owner.name}</p>
          </DetailBox>
        </DetailContainer>
        <DetailBox width={'100%'} height={'40%'} background="">
          <h6>구성원</h6>
          <MemberList members={members} />
        </DetailBox>
        <DetailBox width={'100%'} height={'30%'} background="">
          <h6>프로젝트 기간</h6>{' '}
          <p>
            {item.startDate} ~ {item.endDate}
          </p>
        </DetailBox>
      </DetailContainer>
    </Container>
  );
}

export default ProjectDetail;
