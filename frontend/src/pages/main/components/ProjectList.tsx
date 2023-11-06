import ProjectItem from './ProjectList/ProjectItem';
import { colors } from '@assets/design/colors';
import { ProjectBoxContainer, ProjectContainer, TextContainer } from '@main/MainStyle';

function ProjectList() {
  return (
    <ProjectContainer>
      <TextContainer>
        <h4>최근 프로젝트</h4>
        <p>전체보기</p>
      </TextContainer>
      <ProjectBoxContainer>
        <ProjectItem projectname={'프로젝트 1'} membercount={1} backgroundColor={colors.yellow} />
        <ProjectItem projectname={'프로젝트 2'} membercount={2} backgroundColor={colors.blue} />
        <ProjectItem projectname={'프로젝트 3'} membercount={3} backgroundColor={colors.black} />
      </ProjectBoxContainer>
    </ProjectContainer>
  );
}

export default ProjectList;
