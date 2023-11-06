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
        <ProjectItem
          title={'프로젝트 1'}
          members={['철수', '영희', '민지']}
          tasks={['할 일 1', '할 일 2', '할 일 3', '할 일 4']}
          backgroundColor={colors.yellow}
        />
        <ProjectItem
          title={'프로젝트 2'}
          members={['철수', '영희', '민지']}
          tasks={['할 일 1', '할 일 2', '할 일 3', '할 일 4']}
          backgroundColor={colors.blue}
        />
        <ProjectItem
          title={'프로젝트 3'}
          members={['철수', '영희', '민지']}
          tasks={['할 일 1', '할 일 2', '할 일 3', '할 일 4', '할 일 5', '할 일 6']}
          backgroundColor={colors.black}
        />
      </ProjectBoxContainer>
    </ProjectContainer>
  );
}

export default ProjectList;
