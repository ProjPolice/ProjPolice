import { Container } from "@project/ProjectStyle";
import { ContainerNav } from "@project/ProjectStyle";
import { ContainerContent } from "@project/ProjectStyle";

function ProjectTaskList() {
  return (
    <Container width={"93%"} height={'85%'} background="white">
      <ContainerNav height={'5%'} background="#fed915"/>
      <ContainerContent height={'95%'}/>
    </Container>
  );
}

export default ProjectTaskList;
