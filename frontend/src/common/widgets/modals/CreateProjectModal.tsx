import { colors } from '@assets/design/colors';
import { ColumnItem, Container } from './ModalStyle';
import { ContainerNav } from './ModalStyle';
import { ContainerContent } from './ModalStyle';
import { ContentItem } from './ModalStyle';
import { Segment } from './ModalStyle';
import { InputBox } from './ModalStyle';
import { ContentName } from './ModalStyle';
import { Button } from '@widgets/buttons/ButtonStyle';

function CreateProjectkModal() {
  return (
    <Container width={'30%'} height={'50%'}>
      <ContainerNav height={'15px'} background={colors.primary} />
      <ContentItem width={'75%'} height={'30%'} justifycontent="">
        <h3 style={{ fontWeight: '100', fontSize: '30px' }}>프로젝트 생성</h3>
      </ContentItem>
      <ContainerContent width={'75%'} height={'65%'} background="">
        {/* item 1 : 프로젝트명 */}
        <ContentItem width={'100%'} height={'50px'} justifycontent="">
          <ContentName width={'90px'}>
            <p style={{ fontSize: '14px' }}>프로젝트명</p>
          </ContentName>
          <InputBox width="" flexgrow="1">
            <input
              type="text"
              placeholder="프로젝트명을 입력하세요"
              style={{
                width: '100%',
                height: '90%',
                border: 'none',
                outline: 'none',
              }}
            />
          </InputBox>
        </ContentItem>
        {/* item 2: 멤버 구성 */}
        <ContentItem width={'100%'} height={'50px'} justifycontent="">
          <ContentName width={'90px'}>
            <p style={{ fontSize: '14px' }}>멤버 구성</p>
          </ContentName>
          <InputBox width="" flexgrow="1">
            <input
              type="text"
              placeholder="초대할 멤버의 메일을 입력하세요"
              style={{
                width: '100%',
                height: '90%',
                border: 'none',
                outline: 'none',
              }}
            />
          </InputBox>
        </ContentItem>
        {/* item 3: 담당자 */}
        <ContentItem width={'100%'} height={'50px'} justifycontent="">
          <ContentName width={'90px'}>
            <p style={{ fontSize: '14px' }}>담당자</p>
          </ContentName>
          <InputBox width={'30%'} flexgrow="">
            <input
              type="text"
              placeholder="선택하기"
              style={{
                width: '100%',
                height: '90%',
                border: 'none',
                outline: 'none',
              }}
            />
          </InputBox>
        </ContentItem>

        <Segment width={'100%'} height={'30px'} />
        {/* item4: 작업 일정 */}
        <ContentItem width={'100%'} height={'50px'} justifycontent="space-between">
          <ColumnItem width={'45%'} height={'65px'}>
            <p style={{ fontSize: '14px', margin: '5px 5px 5px 0px' }}>시작일</p>
            <InputBox width={'100%'} flexgrow="">
              <input
                type="text"
                placeholder="YYYY-MM-DD"
                style={{
                  width: '100%',
                  height: '90%',
                  border: 'none',
                  outline: 'none',
                }}
              />
            </InputBox>
          </ColumnItem>
          <ColumnItem width={'45%'} height={'65px'}>
            <p style={{ fontSize: '14px', margin: '5px 5px 5px 0px' }}>종료일</p>
            <InputBox width={'100%'} flexgrow="">
              <input
                type="text"
                placeholder="YYYY-MM-DD"
                style={{
                  width: '100%',
                  height: '90%',
                  border: 'none',
                  outline: 'none',
                }}
              />
            </InputBox>
          </ColumnItem>
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

export default CreateProjectkModal;
