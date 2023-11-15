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
import project from '@api/project';
import { useSetRecoilState } from 'recoil';
import { projectMemberState } from 'state/project';

function AddMemberModal({ visible, handleVisible, projectId }: EpicModalProps) {
  const [local, handleLocal, setLocal] = useTextInput();
  const [domain, handleDomain, setDomain] = useTextInput();
  const setMembers = useSetRecoilState(projectMemberState);

  const resetData = () => {
    setLocal('');
    setDomain('');
  };

  const createTask = () => {
    const email = { memberEmail: local + '@' + domain };

    project
      .memberCreate(projectId, email)
      .then((response) => {
        handleVisible();
        resetData();
        setMembers((prev) => [...prev, response.data]);
      })
      .catch((error) => {
        console.log(error);
        alert('멤버 초대에 실패하였습니다');
      });
  };

  return (
    <>
      {visible && (
        <Container width={'30%'} height={'30%'}>
          <ContainerNav height={'15px'} background={colors.primary} />
          <ContentItem width={'75%'} height={'30%'} justifycontent="">
            <h3 style={{ fontWeight: '100', fontSize: '30px' }}>멤버 초대</h3>
          </ContentItem>
          <ContainerContent width={'75%'} height={'65%'} background="">
            {/* item 3: 작업 일정 */}
            <ContentItem width={'75%'} height={'20%'} justifycontent=""></ContentItem>
            <ContentItem width={'100%'} height={'50%'} justifycontent="">
              <ContentName width={'90px'}>
                <p style={{ fontSize: '14px' }}>이메일</p>
              </ContentName>
              <InputBox width={'25%'} flexgrow="">
                <input
                  type="text"
                  placeholder="example"
                  style={{
                    width: '100%',
                    height: '90%',
                    border: 'none',
                    outline: 'none',
                  }}
                  value={local}
                  onChange={handleLocal}
                />
              </InputBox>
              <p style={{ fontSize: '14px', margin: '0px 5px 0px' }}>@</p>
              <InputBox width={'25%'} flexgrow="">
                <input
                  type="text"
                  placeholder="example.com"
                  style={{
                    width: '100%',
                    height: '90%',
                    border: 'none',
                    outline: 'none',
                  }}
                  value={domain}
                  onChange={handleDomain}
                />
              </InputBox>
            </ContentItem>
          </ContainerContent>
          {/* item 5: 버튼 */}
          <Segment width={'70%'} height={'20%'}>
            <ProjPoliceButton width={50} height={30} context="초대" onClick={createTask} />
            <ProjPoliceButton width={50} height={30} context="취소" onClick={handleVisible} />
          </Segment>
          <Segment width={'100%'} height={'5%'} />
        </Container>
      )}
    </>
  );
}

export default AddMemberModal;
