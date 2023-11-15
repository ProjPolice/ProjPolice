import { TimeLineBar } from '@project/ProjectStyle';
import { threeColorList } from '@assets/design/colors';

import { useSetRecoilState } from 'recoil';
import { selectedIndexState } from 'state/project';
import { TaskTimelineListProps } from '@interfaces/project';
import { getTimelineProgress } from '@utils/getTimelineProgress';

function TaskTimelineList({
  // id,
  //  name,
  startDate,
  endDate,
  epicStart,
  index,
}: TaskTimelineListProps) {
  const setSelectedIndex = useSetRecoilState(selectedIndexState);

  return (
    <TimeLineBar
      width={getTimelineProgress(startDate, endDate, epicStart).width}
      height="10px"
      background={threeColorList[index % 3]}
      onClick={() => setSelectedIndex(index)}
      empty={getTimelineProgress(startDate, endDate, epicStart).empty}
    ></TimeLineBar>
  );
}

export default TaskTimelineList;
