import { colors } from '@assets/design/colors';
import { Container } from './ModalStyle';
import { ContainerNav } from './ModalStyle';
import { ContainerContent } from './ModalStyle';
import { ContentItem } from './ModalStyle';
import { InputBox } from './ModalStyle';
import { ContentName } from './ModalStyle';
import ProjPoliceButton from '@widgets/buttons/ProjPoliceButton';
import { UploadFileProps } from '@interfaces/widgets';
import { useState } from 'react';
import { FileUploader } from 'react-drag-drop-files';
import file from '@api/file';
import { useNumberInput } from 'common/hooks/useNumberInput';
import { useRecoilState } from 'recoil';
import { taskDataState } from 'state/project';
import { TaskData } from '@api/user';

function UploadFileModal({ visible, handleVisible, taskId }: UploadFileProps) {
  const [taskFile, setTaskFile] = useState<File>();
  const [version, handleVersion, setVersion] = useNumberInput(0);
  const [tasks, setTasks] = useRecoilState(taskDataState);

  const handleFile = (file: File) => {
    setTaskFile(file);
  };

  const resetData = () => {
    setTaskFile(undefined);
    setVersion(0);
  };

  const uploadFile = () => {
    if (taskFile) {
      const data = {
        file: taskFile,
        version: Number(version),
      };
      file
        .upload(data, taskId)
        .then((response) => {
          handleVisible();
          resetData();
          const uploadedFile = response.data;

          const updatedTasks = JSON.parse(JSON.stringify(tasks));
          updatedTasks.map((updated: TaskData) => {
            if (updated.id === taskId) {
              updated.file.unshift(uploadedFile);
            }
          });
          setTasks(updatedTasks);
        })
        .catch((error) => {
          console.log(error);
          alert('파일 업로드에 실패하였습니다');
        });
    } else {
      alert('파일을 업로드해주세요');
    }
  };

  return (
    <>
      {visible && (
        <Container width={'30%'} height={'25%'}>
          <ContainerNav height={'15px'} background={colors.primary} />
          <ContentItem width={'75%'} height={'30%'} justifycontent="">
            <h3 style={{ fontWeight: '100', fontSize: '30px' }}>파일 업로드</h3>
          </ContentItem>

          <FileUploader handleChange={handleFile} />

          <ContentItem width="100%" height="10%" justifycontent=""></ContentItem>

          <ContainerContent width={'75%'} height={'25%'} background="">
            <ContentItem width={'100%'} height={'50px'} justifycontent="">
              <ContentName width={'90px'}>
                <p style={{ fontSize: '14px' }}>버전</p>
              </ContentName>
              <InputBox width={'50%'} flexgrow="">
                <input
                  type="number"
                  placeholder="버전을 입력해주세요"
                  style={{
                    width: '100%',
                    height: '90%',
                    border: 'none',
                    outline: 'none',
                  }}
                  value={version}
                  onChange={handleVersion}
                />
              </InputBox>
            </ContentItem>
          </ContainerContent>
          {/* item 5: 버튼 */}
          <div
            style={{
              display: 'flex',
              height: '30%',
              alignItems: 'center',
              width: '100%',
              justifyContent: 'space-evenly',
            }}
          >
            <ProjPoliceButton width={100} height={30} context="업로드" onClick={uploadFile} />
            <ProjPoliceButton width={100} height={30} context="취소" onClick={handleVisible} />
          </div>
        </Container>
      )}
    </>
  );
}

export default UploadFileModal;
