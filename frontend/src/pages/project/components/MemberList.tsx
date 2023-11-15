import { colors } from '@assets/design/colors';
import { DetailContainer } from '@project/ProjectStyle';
import { ProfileBox } from '@project/ProjectStyle';
import { Photo } from '@project/ProjectStyle';

import { MemberListProps } from '@interfaces/project';

function MemberList({ members }: MemberListProps) {
  return (
    <DetailContainer width={'100%'} height={'35%'} background="" flexdirection="row">
      {members.map((member, index) => (
        <ProfileBox key={index}>
          <Photo width={'40px'} height={'40px'} background={colors.yellow} imgurl={member.image} borderradius={'50%'} />
          <p style={{ fontSize: '12px' }}>{member.name}</p>
        </ProfileBox>
      ))}
    </DetailContainer>
  );
}

export default MemberList;
