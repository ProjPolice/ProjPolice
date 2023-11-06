import { Container } from "@project/ProjectStyle";
import { ContainerNav } from "@project/ProjectStyle";
import { ContainerContent } from "@project/ProjectStyle";
import { colors } from '@assets/design/colors';

function ProjectTaskList() {
  return (
    <Container width={"93%"} height={'85%'}>
      <ContainerNav height={'5%'} background={colors.yellow}/>
      <ContainerContent height={'95%'} background=""/>
    </Container>
  );
}

export default ProjectTaskList;
