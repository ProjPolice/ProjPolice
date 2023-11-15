import { colors } from '@assets/design/colors';
import { Container } from './ModalStyle';
import { ContainerNav } from './ModalStyle';
import { ContainerContent } from './ModalStyle';
import { ContentItem } from './ModalStyle';
import { Segment } from './ModalStyle';
import { InputBox } from './ModalStyle';
import { ContentName } from './ModalStyle';
import ProjPoliceButton from '@widgets/buttons/ProjPoliceButton';
import { TaskModalProps } from '@interfaces/widgets';
import { useTextInput } from 'common/hooks/useTextInput';
import task from '@api/task';
import { epicDataState } from 'state/project';
import { useRecoilState } from 'recoil';
import { useEffect, useState } from 'react';
import project, { MemberData } from '@api/project';

function CreateTaskModal({ visible, handleVisible, projectId, epicId }: TaskModalProps) {
  const [name, handleName, setName] = useTextInput();
  const [description, handleDescription, setDescription] = useTextInput();
  const [startDate, handleStartDate, setStartDate] = useTextInput();
  const [endDate, handleEndDate, setEndDate] = useTextInput();

  const [epicData, setEpicData] = useRecoilState(epicDataState);
  const [members, setMembers] = useState<MemberData[]>([]);
  const [selectedMember, setSelectedMember] = useState(0);

  useEffect(() => {
    project.memberdata(projectId).then((response) => {
      setMembers(response.data.members);
    });
  }, []);

  const resetData = () => {
    setName('');
    setDescription('');
    setStartDate('');
    setEndDate('');
  };

  const createTask = () => {
    const data = {
      name: name,
      description: description,
      startDate: startDate,
      endDate: endDate,
      epicId: epicId,
      userId: selectedMember,
    };
    task
      .create(data)
      .then((response) => {
        handleVisible();
        resetData();
        const createdTask = {
          id: response.data.id,
          name: response.data.name,
          startDate: response.data.startDate,
          endDate: response.data.endDate,
        };
        const epicItems = epicData.map((item) => ({ ...item }));
        const updatedEpicIndex = epicItems.findIndex((updatedEpicItem) => updatedEpicItem.id === epicId);
        epicItems[updatedEpicIndex].tasks = [...epicItems[updatedEpicIndex].tasks, createdTask];
        setEpicData(epicItems);
      })
      .catch((error) => {
        console.log(error);
        alert('할 일 생성에 실패하였습니다');
      });
  };

  return (
    <>
      {visible && (
        <Container width={'30%'} height={'55%'}>
          <ContainerNav height={'15px'} background={colors.primary} />
          <ContentItem width={'75%'} height={'30%'} justifycontent="">
            <h3 style={{ fontWeight: '100', fontSize: '30px' }}>작업 생성</h3>
          </ContentItem>
          <ContainerContent width={'75%'} height={'65%'} background="">
            {/* item 2: 할 일 */}
            <ContentItem width={'100%'} height={'50px'} justifycontent="">
              <ContentName width={'90px'}>
                <p style={{ fontSize: '14px' }}>작업명</p>
              </ContentName>
              <InputBox width={'50%'} flexgrow="">
                <input
                  type="text"
                  placeholder="작업 이름을 입력해주세요"
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
                <p style={{ fontSize: '14px' }}>작업 설명</p>
              </ContentName>
              <InputBox width={'50%'} flexgrow="">
                <input
                  type="text"
                  placeholder="작업 설명을 입력해주세요"
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

            <ContentItem width={'100%'} height={'50px'} justifycontent="">
              <ContentName width={'90px'}>
                <p style={{ fontSize: '14px' }}>작업할 사람</p>
              </ContentName>
              {/* member를 다 띄워서 선택하게 */}
              <div style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
                {members.map((member, index) => (
                  <img
                    key={index}
                    src={member.image}
                    width={50}
                    height={50}
                    onClick={() => setSelectedMember(member.id)}
                    style={selectedMember === member.id ? { border: '3px solid red', boxSizing: 'border-box' } : {}}
                  />
                ))}
              </div>
            </ContentItem>
          </ContainerContent>
          {/* item 5: 버튼 */}
          <Segment width={'70%'} height={'20%'}>
            <ProjPoliceButton width={50} height={30} context="생성" onClick={createTask} />
            <ProjPoliceButton width={50} height={30} context="취소" onClick={handleVisible} />
          </Segment>
          <Segment width={'100%'} height={'5%'} />
        </Container>
      )}
    </>
  );
}

export default CreateTaskModal;
