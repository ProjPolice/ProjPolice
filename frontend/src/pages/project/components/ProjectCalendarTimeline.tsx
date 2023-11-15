import { Container } from '@project/ProjectStyle';
import { ContainerNav } from '@project/ProjectStyle';
import { ContainerContent } from '@project/ProjectStyle';
import ProjectCalendar from './ProjectCalendar';
import ProjPoliceButton from '@widgets/buttons/ProjPoliceButton';
import { useEffect, useState } from 'react';
import project, { ProjectData } from '@api/project';
import CreateEpicModal from '@widgets/modals/CreateEpicModal';
import { ProjectIdProps } from '@interfaces/project';

function ProjectCalendarTimeline({ id }: ProjectIdProps) {
  const [items, setItems] = useState<ProjectData>();
  const [visible, setVisible] = useState(false);

  useEffect(() => {
    project.data(id).then((response) => {
      setItems(response.data);
    });
  }, []);

  const handleVisible = () => {
    setVisible(!visible);
  };

  return (
    <Container width={'60%'} height={'90%'}>
      <CreateEpicModal visible={visible} handleVisible={handleVisible} projectId={id} />
      <ContainerNav height={'15%'} background="">
        <p>{items?.name}</p>
        <ProjPoliceButton width={20} height={20} type="bold" context="+" onClick={handleVisible} />
      </ContainerNav>
      <ContainerNav height={'2px'} background="#d8d8d8" />
      <ContainerContent width={'100%'} height={'90%'} background="">
        <ProjectCalendar id={id} />
      </ContainerContent>
    </Container>
  );
}

export default ProjectCalendarTimeline;
