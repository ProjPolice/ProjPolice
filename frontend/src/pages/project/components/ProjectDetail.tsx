import { Container } from "@project/ProjectStyle";
import { ContainerNav } from "@project/ProjectStyle";
import { ContainerContent } from "@project/ProjectStyle";
import { colors } from '@assets/design/colors';

function ProjectDetail() {
  return (
    <Container width={"30%"} height={'90%'}>
      <ContainerNav height={'15%'} background="">
        <p>프로젝트 정보</p> 
      </ContainerNav>
      <ContainerNav height={'0.5%'} background="#d8d8d8"/>
      <ContainerContent height={'90%'} background=""/>
    </Container>
  );
}

export default ProjectDetail;
