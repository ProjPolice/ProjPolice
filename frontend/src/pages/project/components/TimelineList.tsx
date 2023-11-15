import { TimeLineContainer, TimeLineBar } from '@project/ProjectStyle';
import { backgroundColorList } from '@assets/design/colors';
import { TimelineInfoLeft } from '@project/ProjectStyle';

import { useRecoilState, useSetRecoilState } from 'recoil';
import { epicDataState, selectedIndexState } from 'state/project';
import { useEffect } from 'react';
import { TimelineListProps } from '@interfaces/project';
import epic from '@api/epic';
import { getTimelineProgress } from '@utils/getTimelineProgress';
// import TaskTimelineList from './TaskTimelineList';

function TimelineList({ startDate, endDate, projectId }: TimelineListProps) {
  const setSelectedIndex = useSetRecoilState(selectedIndexState);
  const [items, setItems] = useRecoilState(epicDataState);

  useEffect(() => {
    epic
      .data({ start: startDate, end_date: endDate, projectId: projectId })
      .then((response) => {
        setItems(response.data);
      })
      .catch(() => {
        console.log('에러다');
      });
  }, []);

  return (
    <TimeLineContainer width={'100%'} height={'85%'} background="">
      {items.map((item, index) => (
        <TimeLineBar
          key={index}
          width={getTimelineProgress(item.startDate, item.endDate, startDate).width}
          height="%"
          background={backgroundColorList[index % 3]}
          onClick={() => {
            setSelectedIndex((prev) => {
              if (prev === -1 || prev !== item.id) {
                return item.id;
              } else {
                return -1;
              }
            });
          }}
          empty={getTimelineProgress(item.startDate, item.endDate, startDate).empty}
        >
          <TimelineInfoLeft>
            <p style={{ fontSize: '15px' }}>{item.name}</p>
          </TimelineInfoLeft>
          {/* {item.tasks?.map((task, index) => (
            <TaskTimelineList
              key={index}
              id={task.id}
              name={task.name}
              startDate={task.startDate}
              endDate={task.endDate}
              epicStart={item.startDate}
              index={index}
            />
          ))} */}
        </TimeLineBar>
      ))}
    </TimeLineContainer>
  );
}

export default TimelineList;
