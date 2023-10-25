import HeaderBox from './Header/HeaderBox';

import { Container } from '@main/MainStyle';

function Header() {
  return (
    <Container height={'20%'}>
      <HeaderBox count={1} name={'전체 프로젝트'} />
      <HeaderBox count={1} name={'전체 EPIC'} />
      <HeaderBox count={1} name={'전체 TASK'} />
    </Container>
  );
}

export default Header;
