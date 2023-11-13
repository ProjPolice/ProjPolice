import { TimeLineContainer, TimeLineBar } from '@project/ProjectStyle';
import { colors } from '@assets/design/colors';
import { Photo } from '@project/ProjectStyle';
import { TimelineInfoLeft } from '@project/ProjectStyle';

import DefaultProfilePhoto from '@assets/images/ProjPoliceIcon.png';
import DefaultFileIcon from '@assets/icons/Stick.png';
import { useSetRecoilState } from 'recoil';
import { selectedIndexState } from 'state/project';

function TimelineList() {
  const setSelectedIndex = useSetRecoilState(selectedIndexState);

  const items = [
    {
      id: 1,
      title: '할 일 A',
      width: '16.6%',
      background: colors.red,
      profilePhoto: DefaultProfilePhoto,
      fileIcon: DefaultFileIcon,
    },
    {
      id: 2,
      title: '할 일 B',
      width: '60%',
      background: colors.violet,
      profilePhoto: DefaultProfilePhoto,
      fileIcon: DefaultFileIcon,
    },
  ];

  return (
    <TimeLineContainer width={'100%'} height={'85%'} background="">
      {items.map((item, index) => (
        <TimeLineBar
          key={index}
          width={item.width}
          height={'33px'}
          background={item.background}
          onClick={() => setSelectedIndex(index)}
        >
          <TimelineInfoLeft>
            <p style={{ fontSize: '15px' }}>{item.title}</p>
            <Photo
              width={'25px'}
              height={'25px'}
              background={colors.default}
              imgurl={item.profilePhoto}
              borderradius={'50%'}
            />
          </TimelineInfoLeft>
          <Photo width={'25px'} height={'25px'} background={colors.default} imgurl={item.fileIcon} borderradius="" />
        </TimeLineBar>
      ))}
    </TimeLineContainer>
  );
}

export default TimelineList;
