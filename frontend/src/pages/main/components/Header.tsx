import { colors } from '@assets/design/colors';
import HeaderBox from './Header/HeaderBox';

import { Container } from '@main/MainStyle';

function Header() {
  return (
    <Container height={'30%'} background={colors.primary}>
      <HeaderBox
        count={1}
        name={'전체 프로젝트'}
        icon="../../../src/common/assets/icons/Calendar.png"
        backgroundColor={colors.black}
      />
      <HeaderBox
        count={1}
        name={'전체 EPIC'}
        icon="../../../src/common/assets/icons/Menu.png"
        backgroundColor={colors.black}
      />
      <HeaderBox
        count={1}
        name={'전체 TASK'}
        icon="../../../src/common/assets/icons/Stick.png"
        backgroundColor={colors.black}
      />
    </Container>
  );
}

export default Header;
