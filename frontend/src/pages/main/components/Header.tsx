import { colors } from '@assets/design/colors';
import HeaderItem from './Header/HeaderBox';

import { HeaderBackground, HeaderContainer } from '@main/MainStyle';

function Header() {
  return (
    <HeaderBackground>
      <HeaderContainer>
        <HeaderItem
          count={1}
          name={'전체 프로젝트'}
          icon="../../../src/common/assets/icons/Calendar.png"
          backgroundColor={colors.black}
        />
        <HeaderItem
          count={1}
          name={'전체 EPIC'}
          icon="../../../src/common/assets/icons/Menu.png"
          backgroundColor={colors.black}
        />
        <HeaderItem
          count={1}
          name={'전체 TASK'}
          icon="../../../src/common/assets/icons/Stick.png"
          backgroundColor={colors.black}
        />
      </HeaderContainer>
    </HeaderBackground>
  );
}

export default Header;
