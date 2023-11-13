import { colors } from '@assets/design/colors';
import { Container } from './ModalStyle';
import { ContainerNav } from './ModalStyle';
import { ContainerContent } from './ModalStyle';
import { ContentItem } from './ModalStyle';
import { Segment } from './ModalStyle';
import { InputBox } from './ModalStyle';
import { ContentName } from './ModalStyle';
import { Button } from '@widgets/buttons/ButtonStyle';

function CreateTaskModal() {
  return (
    <Container width={'30%'} height={'55%'}>
      <ContainerNav height={'15px'} background={colors.primary} />
      <ContentItem width={'75%'} height={'30%'} justifycontent="">
        <h3 style={{ fontWeight: '100', fontSize: '30px' }}>세부 작업 생성</h3>
      </ContentItem>
      <ContainerContent width={'75%'} height={'65%'} background="">
        {/* item 1 : 프로젝트명 */}
        <ContentItem width={'100%'} height={'50px'} justifycontent="">
          <ContentName width={'90px'}>
            <p style={{ fontSize: '14px' }}>프로젝트</p>
          </ContentName>
          <InputBox width={'50%'} flexgrow="">
            <input
              type="text"
              placeholder="프로젝트를 선택하세요"
              style={{
                width: '100%',
                height: '90%',
                border: 'none',
                outline: 'none',
              }}
            />
          </InputBox>
        </ContentItem>
        {/* item 2: 할 일 */}
        <ContentItem width={'100%'} height={'50px'} justifycontent="">
          <ContentName width={'90px'}>
            <p style={{ fontSize: '14px' }}>할 일</p>
          </ContentName>
          <InputBox width={'50%'} flexgrow="">
            <input
              type="text"
              placeholder="할 일을 선택하세요"
              style={{
                width: '100%',
                height: '90%',
                border: 'none',
                outline: 'none',
              }}
            />
          </InputBox>
        </ContentItem>
        {/* item 3: 작업 일정 */}
        <ContentItem width={'100%'} height={'50px'} justifycontent="">
          <ContentName width={'90px'}>
            <p style={{ fontSize: '14px' }}>작업 일정</p>
          </ContentName>
          <InputBox width={'25%'} flexgrow="">
            <input
              type="text"
              placeholder="YYYY.MM.DD"
              style={{
                width: '100%',
                height: '90%',
                border: 'none',
                outline: 'none',
              }}
            />
          </InputBox>
          <p style={{ fontSize: '14px', margin: '0px 5px 0px' }}>-</p>
          <InputBox width={'25%'} flexgrow="">
            <input
              type="text"
              placeholder="YYYY.MM.DD"
              style={{
                width: '100%',
                height: '90%',
                border: 'none',
                outline: 'none',
              }}
            />
          </InputBox>
        </ContentItem>

        {/* item 3: 작업명 */}
        <ContentItem width={'100%'} height={'50px'} justifycontent="">
          <ContentName width={'90px'}>
            <p style={{ fontSize: '14px' }}>작업명</p>
          </ContentName>
          <InputBox width="" flexgrow="1">
            <input
              type="text"
              placeholder="세부 작업명을 입력하세요"
              style={{
                width: '100%',
                height: '90%',
                border: 'none',
                outline: 'none',
              }}
            />
          </InputBox>
        </ContentItem>

        {/* item 3: 파일 추가 */}
        <ContentItem width={'100%'} height={'50px'} justifycontent="">
          <ContentName width={'90px'}>
            <p style={{ fontSize: '14px' }}>파일 추가</p>
          </ContentName>
          <input type="file" />
        </ContentItem>
      </ContainerContent>
      {/* item 5: 버튼 */}
      <Segment width={'70%'} height={'20%'}>
        <Button width={'40%'} height={'45px'} fontsize={'16px'}>
          생성
        </Button>
        <Button width={'40%'} height={'45px'} fontsize={'16px'}>
          취소
        </Button>
      </Segment>
      <Segment width={'100%'} height={'5%'} />
    </Container>
  );
}

export default CreateTaskModal;
