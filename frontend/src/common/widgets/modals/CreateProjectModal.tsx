import { colors } from '@assets/design/colors';
import { ColumnItem, Container } from './ModalStyle';
import { ContainerNav } from './ModalStyle';
import { ContainerContent } from './ModalStyle';
import { ContentItem } from './ModalStyle';
import { Segment } from './ModalStyle';
import { InputBox } from './ModalStyle';
import { ContentName } from './ModalStyle';
import ProjPoliceButton from '@widgets/buttons/ProjPoliceButton';
import { ModalProps } from '@interfaces/widgets';
import { useTextInput } from 'common/hooks/useTextInput';
import project from '@api/project';

function CreateProjectkModal({ visible, handleVisible }: ModalProps) {
  const [name, handleName] = useTextInput();
  const [description, handleDescription] = useTextInput();
  const [startDate, handleStartDate] = useTextInput();
  const [endDate, handleEndDate] = useTextInput();

  const createProject = () => {
    const data = {
      name: name,
      description: description,
      startDate: startDate,
      endDate: endDate,
    };
    project.create(data).then((response) => console.log(response.data));
  };

  return (
    <>
      {visible && (
        <Container width={'30%'} height={'50%'}>
          <ContainerNav height={'15px'} background={colors.primary} />
          <ContentItem width={'75%'} height={'30%'} justifycontent="">
            <h3 style={{ fontWeight: '100', fontSize: '30px' }}>프로젝트 생성</h3>
          </ContentItem>
          <ContainerContent width={'75%'} height={'65%'} background="">
            <ContentItem width={'100%'} height={'50px'} justifycontent="">
              <ContentName width={'90px'}>
                <p style={{ fontSize: '14px' }}>프로젝트명</p>
              </ContentName>
              <InputBox width="" flexgrow="1">
                <input
                  type="text"
                  placeholder="프로젝트명을 입력해주세요."
                  style={{
                    width: '100%',
                    height: '90%',
                    border: 'none',
                    outline: 'none',
                  }}
                  value={name}
                  onChange={handleName}
                />
              </InputBox>
            </ContentItem>
            <Segment width={'100%'} height={'30px'} />
            <ContentItem width={'100%'} height={'50px'} justifycontent="">
              <ContentName width={'90px'}>
                <p style={{ fontSize: '14px' }}>프로젝트 설명</p>
              </ContentName>
              <InputBox width="" flexgrow="1">
                <input
                  type="text"
                  placeholder="프로젝트의 설명을 입력해주세요."
                  style={{
                    width: '100%',
                    height: '90%',
                    border: 'none',
                    outline: 'none',
                  }}
                  value={description}
                  onChange={handleDescription}
                />
              </InputBox>
            </ContentItem>

            <Segment width={'100%'} height={'30px'} />

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
                    value={startDate}
                    onChange={handleStartDate}
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
                    value={endDate}
                    onChange={handleEndDate}
                  />
                </InputBox>
              </ColumnItem>
            </ContentItem>
          </ContainerContent>
          <Segment width={'70%'} height={'20%'}>
            <ProjPoliceButton width={50} height={30} onClick={createProject} context="생성" />
            <ProjPoliceButton width={50} height={30} onClick={handleVisible} context="취소" />
          </Segment>
          <Segment width={'100%'} height={'5%'} />
        </Container>
      )}
    </>
  );
}

export default CreateProjectkModal;
