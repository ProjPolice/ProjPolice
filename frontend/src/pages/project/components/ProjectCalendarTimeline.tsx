import { Container } from '@project/ProjectStyle';
import { ContainerNav } from '@project/ProjectStyle';
import { ContainerContent } from '@project/ProjectStyle';
import ProjectCalendar from './ProjectCalendar';
import ProjPoliceButton from '@widgets/buttons/ProjPoliceButton';
import { useEffect, useState } from 'react';
import project from '@api/project';
import CreateEpicModal from '@widgets/modals/CreateEpicModal';
import { ProjectIdProps } from '@interfaces/project';
import { useRecoilState, useSetRecoilState } from 'recoil';
import { currentProjectOwner, projectDataState } from 'state/project';

function ProjectCalendarTimeline({ projectId }: ProjectIdProps) {
  const [items, setItems] = useRecoilState(projectDataState);
  const [visible, setVisible] = useState(false);
  const setCurrentProjectOwner = useSetRecoilState(currentProjectOwner);

  useEffect(() => {
    project.data(projectId).then((response) => {
      setItems(response.data);
      setCurrentProjectOwner(response.data.owner.id);
    });
  }, []);

  const handleVisible = () => {
    setVisible(!visible);
  };

  return (
    <Container width={'60%'} height={'90%'}>
      <CreateEpicModal visible={visible} handleVisible={handleVisible} projectId={projectId} />
      <ContainerNav height={'15%'} background="">
        <p>{items?.name}</p>
        <ProjPoliceButton width={20} height={20} type="bold" context="+" onClick={handleVisible} />
      </ContainerNav>
      <ContainerNav height={'2px'} background="#d8d8d8" />
      <ContainerContent width={'100%'} height={'90%'} background="">
        <ProjectCalendar projectId={projectId} />
      </ContainerContent>
    </Container>
  );
}

export default ProjectCalendarTimeline;
