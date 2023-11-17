import { threeColorList } from '@assets/design/colors';
import { ProjectItemProps } from '@interfaces/main';
import { ProjectBox, ProjectBoxMember, ProjectBoxTask, ProjectBoxTitle } from '@main/MainStyle';
import { useNavigate } from 'react-router-dom';

function ProjectItem({ id, name, description, index }: ProjectItemProps) {
  const navigate = useNavigate();

  const moveToDetail = () => {
    navigate(`project/${id}`);
  };

  return (
    <ProjectBox backgroundColor={threeColorList[index]} onClick={moveToDetail}>
      <ProjectBoxTitle>
        <h6>{name}</h6>
      </ProjectBoxTitle>
      <ProjectBoxMember>
        <p>{description}</p>
        {/* {members.map((member, index) => (
          <p key={index}>{member.name}</p>
        ))} */}
      </ProjectBoxMember>
      <ProjectBoxTask>
        {/* {tasks.map((task, index) => (
          <LeftLinedBox width="45%" height="30%" backgroundColor={backgroundColor} key={index}>
            <p>{task.name}</p>
          </LeftLinedBox>
        ))} */}
      </ProjectBoxTask>
    </ProjectBox>
  );
}

export default ProjectItem;
