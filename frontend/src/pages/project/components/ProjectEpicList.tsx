import { Container } from '@project/ProjectStyle';
import { ContainerNav } from '@project/ProjectStyle';
import { colors } from '@assets/design/colors';
import { EpicContainer } from '@project/ProjectStyle';
import { EpicItem } from '@project/ProjectStyle';

function ProjectEpicList() {
  return (
    <Container width={'93%'} height={'90%'}>
      <ContainerNav height={'5%'} background={colors.yellow} />
      <EpicContainer width={'98%'} height={'85%'}>
        <EpicItem width={'100%'} height={'20%'} background={colors.light}>
          <p>작업 A</p> <p>할 일 A</p> <p>담당자</p> <p>YYYY.MM.DD</p> <p>파일.format</p>
        </EpicItem>
        <EpicItem width={'100%'} height={'20%'} background={colors.light}>
          <p>작업 A</p> <p>할 일 A</p> <p>담당자</p> <p>YYYY.MM.DD</p> <p>파일.format</p>
        </EpicItem>
      </EpicContainer>
    </Container>
  );
}

export default ProjectEpicList;
