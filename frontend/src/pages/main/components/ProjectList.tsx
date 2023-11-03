import ProjectItem from './ProjectList/ProjectItem';
import { colors } from '@assets/design/colors';
import { ProjectContainer } from '@main/MainStyle';

function ProjectList() {
  return (
    <ProjectContainer height={'50%'} background={colors.white}>
      <ProjectItem projectname={'프로젝트 1'} membercount={1} backgroundColor={colors.yellow} />
      <ProjectItem projectname={'프로젝트 2'} membercount={2} backgroundColor={colors.blue} />
      <ProjectItem projectname={'프로젝트 3'} membercount={3} backgroundColor={colors.black} />
    </ProjectContainer>
  );
}

export default ProjectList;
