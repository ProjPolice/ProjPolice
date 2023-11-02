import { Container } from "@project/ProjectStyle";
import { ContainerNav } from "@project/ProjectStyle";
import { ContainerContent } from "@project/ProjectStyle";


function ProjectDetail() {
  return (
    <Container width={"30%"} height={'90%'} background="#ffffff">
      <ContainerNav height={'15%'} background="">
        <h3>프로젝트 정보</h3> 
      </ContainerNav>
      <ContainerNav height={'0.5%'} background="#d8d8d8"/>
      <ContainerContent height={'90%'}/>
    </Container>
  );
}

export default ProjectDetail;
