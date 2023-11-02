import ProjectItem from './ProjectList/ProjectItem';

import { ProjectContainer } from '@main/MainStyle';


function ProjectList( ) {
  return (
    <ProjectContainer height={'50%'} background="white">
      <ProjectItem projectname={'프로젝트 1'} membercount={1} backgroundColor="#FED915"/>
      <ProjectItem projectname={'프로젝트 2'} membercount={2} backgroundColor="#3056D3"/>
      <ProjectItem projectname={'프로젝트 3'} membercount={3} backgroundColor="#000000"/>
    </ProjectContainer>
  );
}

export default ProjectList;
