import { Container } from "@project/ProjectStyle";
import { ContainerNav } from "@project/ProjectStyle";
// import { colors } from '@assets/design/colors';
import { DetailContainer } from "@project/ProjectStyle";
import { DetailBox } from "@project/ProjectStyle";
import MemberList from "./MemberList";

function ProjectDetail() {
  return (
    <Container width={"30%"} height={'90%'}>
      <ContainerNav height={'14.5%'} background="">
        <p>프로젝트 정보</p> 
      </ContainerNav>
      <ContainerNav height={'1.5px'} background="#d8d8d8"/>
      <DetailContainer width={'100%'} height={'85%'} background="" flexdirection="column">
        <DetailContainer width={'100%'} height={'30%'} background="" flexdirection="row">
          <DetailBox width={'50%'} height={'100%'} background="">
            <h6>프로젝트명</h6> <p>제목몇자까지</p>
          </DetailBox>
          <DetailBox width={'50%'} height={'100%'} background=""> 
            <h6>담당자</h6> <p>프로젝트리더</p> 
          </DetailBox>
        </DetailContainer>
          <DetailBox width={'100%'} height={'40%'} background="">
            <h6>구성원</h6>
            <MemberList/>
          </DetailBox>
          <DetailBox width={'100%'} height={'30%'} background="">
            <h6>프로젝트 기간</h6> <p>YYYY.MM.DD - YYYY.MM.DD</p>
          </DetailBox>
      </DetailContainer>
    </Container>
  );
}

export default ProjectDetail;
