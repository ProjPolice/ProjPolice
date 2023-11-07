import { TimeLineContainer, TimeLineBar } from '@project/ProjectStyle';
import { colors } from '@assets/design/colors';
import { Photo } from '@project/ProjectStyle';
import { TimelineInfoLeft } from '@project/ProjectStyle';
import DefaultProfilePhoto from '../../../../src/common/assets/icons/Logo.png';
import DefaultFileIcon from '../../../../src/common/assets/icons/Stick.png';

function TimelineList() {
  return (
    <TimeLineContainer width={'100%'} height={'85%'} background="">
      {/* timeline dummy 1 */}
      <TimeLineBar width={'16.6%'} height={'33px'} background={colors.red}>
        <TimelineInfoLeft>
          <p style={{ fontSize: '15px' }}>할 일 A</p>
          <Photo
            width={'25px'}
            height={'25px'}
            background={colors.default}
            imgurl={DefaultProfilePhoto}
            borderradius={'50%'}
          />
        </TimelineInfoLeft>
        <Photo width={'25px'} height={'25px'} background={colors.default} imgurl={DefaultFileIcon} borderradius="" />
      </TimeLineBar>
      {/* timeline dummy 2 */}
      <TimeLineBar width={'60%'} height={'33px'} background={colors.violet}>
        <TimelineInfoLeft>
          <p style={{ fontSize: '15px' }}>할 일 A</p>
          <Photo
            width={'25px'}
            height={'25px'}
            background={colors.default}
            imgurl={DefaultProfilePhoto}
            borderradius={'50%'}
          />
        </TimelineInfoLeft>
        <Photo width={'25px'} height={'25px'} background={colors.default} imgurl={DefaultFileIcon} borderradius="" />
      </TimeLineBar>
    </TimeLineContainer>
  );
}

export default TimelineList;
