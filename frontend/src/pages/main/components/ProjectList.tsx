import { Container } from '@main/MainStyle';
import ProjectItem from './ProjectList/ProjectItem';

function ProjectList() {
  return (
    <Container height="30%">
      <p>프로젝트 목록</p>
      <ProjectItem />
    </Container>
  );
}

export default ProjectList;
