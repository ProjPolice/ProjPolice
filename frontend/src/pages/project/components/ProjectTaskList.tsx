import { Container, ContainerNavContext } from '@project/ProjectStyle';
import { ContainerNav } from '@project/ProjectStyle';
import { colors } from '@assets/design/colors';
import { EpicContainer } from '@project/ProjectStyle';
import { EpicItem } from '@project/ProjectStyle';
import { TaskInfoStyle } from '@project/ProjectStyle';

import task from '@api/task';
import { useEffect, useState } from 'react';
import { EpicDetailProps } from '@interfaces/project';
import { parseStatus } from '@utils/parseStatus';
import ProjPoliceButton from '@widgets/buttons/ProjPoliceButton';
import UploadFileModal from '@widgets/modals/UploadFileModal';
import { useRecoilState, useRecoilValue } from 'recoil';
import { taskDataState } from 'state/project';
import FileIcon from '@widgets/FileIcon';
import FileModal from '@widgets/modals/FileModal';
import { userIdState } from 'state/user';

function ProjectTaskList({ projectId, epicId }: EpicDetailProps) {
  const [tasks, setTasks] = useRecoilState(taskDataState);
  const [uploadModalvisible, setUploadModalVisible] = useState(false);
  const [fileModalVisible, setFileModalVisible] = useState(false);
  const [selectedTask, setSelectedTask] = useState(-1);
  const userId = useRecoilValue(userIdState);

  const handleSelectedTask = (index: number) => {
    if (selectedTask === -1 && index === selectedTask) {
      setSelectedTask(index);
    } else {
      setSelectedTask(-1);
    }
  };

  useEffect(() => {
    if (epicId !== -1) {
      task
        .data(projectId, epicId)
        .then((response) => {
          setTasks(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }, [projectId, epicId]);

  return (
    <Container width={'93%'} height={'90%'}>
      <FileModal
        visible={fileModalVisible}
        handleVisible={() => setFileModalVisible(!fileModalVisible)}
        taskId={selectedTask}
      />
      <UploadFileModal
        visible={uploadModalvisible}
        handleVisible={() => setUploadModalVisible(!uploadModalvisible)}
        taskId={selectedTask}
      />
      <ContainerNav height={'10%'} background={colors.yellow}>
        <ContainerNavContext>
          <TaskInfoStyle>
            <p>작업명</p>
          </TaskInfoStyle>
          <TaskInfoStyle>
            <p>상태</p>
          </TaskInfoStyle>
          <TaskInfoStyle>
            <p>할 일 항목</p>
          </TaskInfoStyle>
          <TaskInfoStyle>
            <p>프로젝트</p>
          </TaskInfoStyle>
          <TaskInfoStyle>
            <p>파일</p>
          </TaskInfoStyle>
          <TaskInfoStyle>
            <p>업로드</p>
          </TaskInfoStyle>
        </ContainerNavContext>
      </ContainerNav>
      <EpicContainer width={'96%'} height={'85%'}>
        {epicId !== -1 ? (
          tasks.map((task, index) => (
            <EpicItem key={index} width={'100%'} height={'21%'} background={colors.light}>
              <TaskInfoStyle>
                <p>{task.name}</p>
              </TaskInfoStyle>
              <TaskInfoStyle>
                <p>{parseStatus(task.status)}</p>
              </TaskInfoStyle>
              <TaskInfoStyle>
                <p>{task.epic?.name}</p>
              </TaskInfoStyle>
              <TaskInfoStyle>
                <p>{task.endDate}</p>
              </TaskInfoStyle>
              <TaskInfoStyle>
                {task.file?.extension && (
                  <FileIcon
                    extension={task.file.extension}
                    onClick={() => {
                      setFileModalVisible(!fileModalVisible);
                      setSelectedTask(task.id);
                    }}
                    size={30}
                  />
                )}
              </TaskInfoStyle>
              <TaskInfoStyle>
                {task.member?.id === userId && (
                  <ProjPoliceButton
                    width={30}
                    height={20}
                    context="+"
                    onClick={() => {
                      setUploadModalVisible(!uploadModalvisible);
                      handleSelectedTask(task.id);
                    }}
                  ></ProjPoliceButton>
                )}
              </TaskInfoStyle>
            </EpicItem>
          ))
        ) : (
          <div
            style={{ width: '100%', height: '100%', display: 'flex', justifyContent: 'center', alignItems: 'center' }}
          >
            <p>할 일을 선택해주세요</p>
          </div>
        )}
      </EpicContainer>
    </Container>
  );
}

export default ProjectTaskList;
