import HeaderBox from './Header/HeaderBox';

import { Container } from '@main/MainStyle';

function Header() {
  return (
    <Container height={'20%'} background="#2391EE">
      <HeaderBox count={1} name={'전체 프로젝트'} icon="../../../src/common/assets/icons/Calendar.png" backgroundColor="#000000" />
      <HeaderBox count={1} name={'전체 EPIC'} icon="../../../src/common/assets/icons/Menu.png" backgroundColor="#FF7474" />
      <HeaderBox count={1} name={'전체 TASK'} icon="../../../src/common/assets/icons/Stick.png" backgroundColor="#FDB120" />
    </Container>
  );
}

export default Header;
