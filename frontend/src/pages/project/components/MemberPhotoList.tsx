import { colors } from '@assets/design/colors';
import { DetailContainer } from '@project/ProjectStyle';
// import { Photo } from '@project/ProjectStyle';
// import DefaultProfilePhoto from '../../../src/common/assets/icons/Logo.png';

function MemberPhotoList() {
  return (
    <DetailContainer width={'100%'} height={'35%'} background={colors.default} flexdirection={'row'}>
      {/* <Photo
        width={'40px'}
        height={'40px'}
        background={colors.default}
        imgurl={DefaultProfilePhoto}
        borderradius={'50%'}
      />
      <Photo width={'40px'} height={'40px'} background={colors.default} imgurl="" borderradius={'50%'} /> */}
    </DetailContainer>
  );
}

export default MemberPhotoList;
