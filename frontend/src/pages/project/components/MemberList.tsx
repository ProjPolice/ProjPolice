import { colors } from '@assets/design/colors';
import { DetailContainer } from '@project/ProjectStyle';
import { ProfileBox } from '@project/ProjectStyle';
import { Photo } from '@project/ProjectStyle';

import DefaultProfilePhoto from '@assets/images/ProjPoliceIcon.png';

function MemberList() {
  return (
    <DetailContainer width={'100%'} height={'35%'} background="" flexdirection="row">
      <ProfileBox>
        <Photo
          width={'40px'}
          height={'40px'}
          background={colors.yellow}
          imgurl={DefaultProfilePhoto}
          borderradius={'50%'}
        />
        <p style={{ fontSize: '12px' }}>멤버1</p>
      </ProfileBox>
      <ProfileBox>
        <Photo
          width={'40px'}
          height={'40px'}
          background={colors.yellow}
          imgurl={DefaultProfilePhoto}
          borderradius={'50%'}
        />
        <p style={{ fontSize: '12px' }}>멤버1</p>
      </ProfileBox>
    </DetailContainer>
  );
}

export default MemberList;
