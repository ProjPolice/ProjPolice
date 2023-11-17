import { colors } from '@assets/design/colors';
import { Container } from './ModalStyle';
import { ContainerNav } from './ModalStyle';
import { ContainerContent } from './ModalStyle';
import { ContentItem } from './ModalStyle';
import { Segment } from './ModalStyle';
import { InputBox } from './ModalStyle';
import { ContentName } from './ModalStyle';
import ProjPoliceButton from '@widgets/buttons/ProjPoliceButton';
import { EpicModalProps } from '@interfaces/widgets';
import { useTextInput } from 'common/hooks/useTextInput';
import epic from '@api/epic';
import { useRecoilState } from 'recoil';
import { epicDataState } from 'state/project';

function CreateEpicModal({ visible, handleVisible, projectId }: EpicModalProps) {
  const [name, handleName, setName] = useTextInput();
  const [description, handleDescription, setDescription] = useTextInput();
  const [startDate, handleStartDate, setStartDate] = useTextInput();
  const [endDate, handleEndDate, setEndDate] = useTextInput();

  const [epicItems, setEpicItems] = useRecoilState(epicDataState);

  const resetData = () => {
    setName('');
    setDescription('');
    setStartDate('');
    setEndDate('');
  };

  const createEpic = () => {
    const data = {
      name: name,
      description: description,
      startDate: startDate,
      endDate: endDate,
      projectId: projectId,
    };
    epic
      .create(data)
      .then((response) => {
        handleVisible();
        resetData();
        const createdEpic = {
          id: response.data.id,
          name: response.data.name,
          startDate: response.data.startDate,
          endDate: response.data.endDate,
          tasks: [],
        };
        const updatedEpicItems = [...epicItems, createdEpic];
        setEpicItems(updatedEpicItems);
      })
      .catch(() => {
        alert('할 일 생성에 실패하였습니다');
      });
  };

  return (
    <>
      {visible && (
        <Container width={'30%'} height={'30%'}>
          <ContainerNav height={'15px'} background={colors.primary} />
          <ContentItem width={'75%'} height={'30%'} justifycontent="">
            <h3 style={{ fontWeight: '100', fontSize: '30px' }}>할 일 생성</h3>
          </ContentItem>
          <ContainerContent width={'75%'} height={'65%'} background="">
            {/* item 2: 할 일 */}
            <ContentItem width={'100%'} height={'50px'} justifycontent="">
              <ContentName width={'90px'}>
                <p style={{ fontSize: '14px' }}>이름</p>
              </ContentName>
              <InputBox width={'50%'} flexgrow="">
                <input
                  type="text"
                  placeholder="할 일 이름을 입력해주세요"
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
            <ContentItem width={'100%'} height={'50px'} justifycontent="">
              <ContentName width={'90px'}>
                <p style={{ fontSize: '14px' }}>설명</p>
              </ContentName>
              <InputBox width={'50%'} flexgrow="">
                <input
                  type="text"
                  placeholder="할 일 설명을 입력해주세요"
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
            {/* item 3: 작업 일정 */}
            <ContentItem width={'100%'} height={'50px'} justifycontent="">
              <ContentName width={'90px'}>
                <p style={{ fontSize: '14px' }}>작업 일정</p>
              </ContentName>
              <InputBox width={'25%'} flexgrow="">
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
              <p style={{ fontSize: '14px', margin: '0px 5px 0px' }}>-</p>
              <InputBox width={'25%'} flexgrow="">
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
            </ContentItem>
          </ContainerContent>
          {/* item 5: 버튼 */}
          <Segment width={'70%'} height={'20%'}>
            <ProjPoliceButton width={50} height={30} context="생성" onClick={createEpic} />
            <ProjPoliceButton width={50} height={30} context="취소" onClick={handleVisible} />
          </Segment>
          <Segment width={'100%'} height={'5%'} />
        </Container>
      )}
    </>
  );
}

export default CreateEpicModal;
