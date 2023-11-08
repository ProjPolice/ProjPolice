import { colors } from '@assets/design/colors';
import { Container } from './ModalStyle';
import { ContainerNav } from './ModalStyle';
import { ContainerContent } from './ModalStyle';
import { ContentItem } from './ModalStyle';
import { Segment } from './ModalStyle';

function CreateTaskModal() {
  return (
    <Container width={'30%'} height={'50%'}>
      <ContainerNav height={'5%'} background={colors.primary} />
      <ContainerContent width={'80%'} height={'95%'} background="">
        <Segment width={'100%'} height={'10%'} />
        <h3>모달입니다</h3>
        <Segment width={'100%'} height={'10%'} />
        <ContentItem width={'60%'} height={'10%'}>
          <p>프로젝트명</p> <p>텍스트박스</p>
        </ContentItem>
        <ContentItem width={'60%'} height={'10%'}>
          <p>할 일</p> <p>텍스트박스</p>
        </ContentItem>
        <ContentItem width={'60%'} height={'10%'}>
          <p>작업 일정</p> <p>텍스트박스</p>
        </ContentItem>
      </ContainerContent>
    </Container>
  );
}

export default CreateTaskModal;
