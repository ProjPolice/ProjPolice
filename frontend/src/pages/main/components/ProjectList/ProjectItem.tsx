import { ProjectItemListProps } from '@interfaces/main';

import { ProjectBox } from '@main/MainStyle';

function ProjectItem({ backgroundColor, projectname, membercount }: ProjectItemListProps) {
  return (
    <ProjectBox backgroundColor={backgroundColor}>
      <div style={{ flex: 1 }}></div>
      <div style={{ flex: 1, alignSelf: 'start' }}>
        <p>{projectname}</p>
      </div>
      <div style={{ flex: 2 }}>
        <p>{membercount}</p>
      </div>
      <div style={{ flex: 3 }}>
        <p>{projectname}</p>
      </div>
    </ProjectBox>
  );
}

export default ProjectItem;
