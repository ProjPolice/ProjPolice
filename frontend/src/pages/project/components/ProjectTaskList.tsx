import { colors } from '@assets/design/colors';
import { Container } from '@project/ProjectStyle';
import { ContainerNav } from '@project/ProjectStyle';
import { ContainerContent } from '@project/ProjectStyle';

function ProjectTaskList() {
  return (
    <Container width={'93%'} height={'85%'} background={colors.white}>
      <ContainerNav height={'5%'} background={colors.yellow} />
      <ContainerContent height={'95%'} />
    </Container>
  );
}

export default ProjectTaskList;
