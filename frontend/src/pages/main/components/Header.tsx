import { colors } from '@assets/design/colors';
import HeaderItem from './Header/HeaderBox';

import Calendar from '@assets/icons/Calendar.png';
import Menu from '@assets/icons/Menu.png';
import Stick from '@assets/icons/Stick.png';

import { HeaderBackground, HeaderContainer } from '@main/MainStyle';

function Header() {
  return (
    <HeaderBackground>
      <HeaderContainer>
        <HeaderItem count={1} name={'전체 프로젝트'} icon={Calendar} backgroundColor={colors.black} />
        <HeaderItem count={1} name={'전체 EPIC'} icon={Menu} backgroundColor={colors.black} />
        <HeaderItem count={1} name={'전체 TASK'} icon={Stick} backgroundColor={colors.black} />
      </HeaderContainer>
    </HeaderBackground>
  );
}

export default Header;
