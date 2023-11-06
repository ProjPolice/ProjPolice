import { ProjectItemProps } from '@interfaces/main';

import { LeftLinedBox, ProjectBox, ProjectBoxMember, ProjectBoxTask, ProjectBoxTitle } from '@main/MainStyle';

function ProjectItem({ backgroundColor, title, members, tasks }: ProjectItemProps) {
  return (
    <ProjectBox backgroundColor={backgroundColor}>
      <ProjectBoxTitle>
        <h6>{title}</h6>
      </ProjectBoxTitle>
      <ProjectBoxMember>
        {members.map((member) => (
          <p>{member}</p>
        ))}
      </ProjectBoxMember>
      <ProjectBoxTask>
        {tasks.map((task) => (
          <LeftLinedBox width="45%" height="30%" backgroundColor={backgroundColor}>
            <p>{task}</p>
          </LeftLinedBox>
        ))}
      </ProjectBoxTask>
    </ProjectBox>
  );
}

export default ProjectItem;
