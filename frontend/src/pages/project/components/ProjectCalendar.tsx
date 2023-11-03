import { colors } from '@assets/design/colors';
import { Container } from '@project/ProjectStyle';
import { ContainerNav } from '@project/ProjectStyle';
import { ContainerContent } from '@project/ProjectStyle';

function ProjectCalendar() {
  return (
    <Container
      width={'97%'}
      height={'95%'}
      background={colors.light}
      style={{ boxShadow: 'none', borderRadius: '0px', border: 'none' }}
    >
      <ContainerNav height={'15%'} background={colors.primary} style={{ justifyContent: 'center' }}>
        <p style={{ color: colors.white }}>YYYY년 M월 W주차</p>
      </ContainerNav>
      {/* <ContainerNav height={'0.5%'} background="#d8d8d8"/> */}
      <ContainerContent height={'85%'} />
    </Container>
  );
}

export default ProjectCalendar;
