import { colors } from '@assets/design/colors';
import { TaskBoxContainer, TaskContainer, TextContainer } from '@main/MainStyle';
import { useNavigate } from 'react-router-dom';

import TaskItem from '@main/components/TaskList/TaskItem';
import { useEffect, useState } from 'react';
import user, { TaskData } from '@api/user';

function TaskList() {
  const navigate = useNavigate();
  const [items, setItems] = useState<TaskData[]>([]);

  const handleNavigateToTask = () => {
    navigate('/task');
  };

  useEffect(() => {
    user.tasks().then((response) => {
      setItems(response.data.tasks);
    });
  }, []);

  return (
    <TaskContainer>
      <TextContainer>
        <div style={{ display: 'flex', alignItems: 'center', gap: '5%', width: '15%' }}>
          <h4>작업</h4>
          <h6>총 {items.length}건</h6>
        </div>
        <p onClick={handleNavigateToTask} style={{ cursor: 'pointer' }}>
          전체보기
        </p>
      </TextContainer>
      <TaskBoxContainer>
        <TaskItem items={items.slice(0, 4)} backgroundColor={colors.yellow} />
      </TaskBoxContainer>
    </TaskContainer>
  );
}

export default TaskList;
