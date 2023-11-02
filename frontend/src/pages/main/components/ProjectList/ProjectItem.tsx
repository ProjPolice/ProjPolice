import { ProjectItemListProps } from '@interfaces/main';

import { ProjectBox } from '@main/MainStyle';


function ProjectItem({ backgroundColor, projectname, membercount }: ProjectItemListProps) {
  return (
    <ProjectBox backgroundColor={backgroundColor}>
      <div style={{ flex: 1 }}>
      </div>
      <div style={{ flex: 1 }}>
        <p>{membercount}</p>
      </div>
      <div style={{ flex: 1 }}>
        <p>{projectname}</p>
      </div>
      <div style={{ flex: 1 }}>
        <p>{projectname}</p>
      </div>
    </ProjectBox>
  );
}

export default ProjectItem;
