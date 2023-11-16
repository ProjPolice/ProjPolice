import { Container } from '@project/ProjectStyle';
import { ContainerNav } from '@project/ProjectStyle';
import { DetailContainer } from '@project/ProjectStyle';
import { DetailBox } from '@project/ProjectStyle';
import ProjPoliceButton from '@widgets/buttons/ProjPoliceButton';
import CreateTaskModal from '@widgets/modals/CreateTaskModal';
import { useEffect, useState } from 'react';
import { EpicDetailProps } from '@interfaces/project';
import epic, { TodoData } from '@api/epic';
import task, { TasksData } from '@api/task';

function EpicDetail({ projectId, epicId }: EpicDetailProps) {
  const [visible, setVisible] = useState(false);

  const [item, setItem] = useState<TodoData>();
  const [tasks, setTasks] = useState<TasksData[]>([]);

  useEffect(() => {
    epic
      .tododata(epicId)
      .then((response) => {
        setItem(response.data);
      })
      .catch((error) => {
        console.log(error);
      });

    task
      .data(projectId, epicId)
      .then((response) => {
        setTasks(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [epicId]);

  const handleVisible = () => {
    setVisible(!visible);
  };

  return (
    <Container width={'30%'} height={'90%'}>
      <CreateTaskModal visible={visible} handleVisible={handleVisible} projectId={projectId} epicId={epicId} />
      <ContainerNav height={'14.5%'} background="">
        <h5>할 일 정보</h5>
        <ProjPoliceButton width={20} height={20} context="+" onClick={handleVisible} type="bold" />
      </ContainerNav>
      <ContainerNav height={'1.5px'} background="#d8d8d8" />
      <DetailContainer width={'100%'} height={'85%'} background="" flexdirection="column">
        <DetailContainer width={'100%'} height={'30%'} background="" flexdirection="row">
          <DetailBox width={'50%'} height={'100%'} background="">
            <h6>이름</h6>
            <p>{item?.name}</p>
          </DetailBox>
          <DetailBox width={'50%'} height={'100%'} background="">
            <h6>설명</h6>
            <p>{item?.description}</p>
          </DetailBox>
        </DetailContainer>
        <DetailBox width={'100%'} height={'40%'} background="">
          <h6>일정</h6>
          <div>
            <p>
              {item?.startDate} ~ {item?.endDate}
            </p>
          </div>
        </DetailBox>
        <DetailBox width={'100%'} height={'40%'} background="">
          <h6>작업</h6>
          <div style={{ display: 'flex' }}>
            <p>{tasks.length} 건</p>
          </div>
        </DetailBox>
      </DetailContainer>
    </Container>
  );
}

export default EpicDetail;
