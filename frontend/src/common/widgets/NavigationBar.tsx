import { colors } from '@assets/design/colors';
import styled from '@emotion/styled';

const NavigationBar = () => {
  return (
    <NavBar>
      <Segment />
      <Segment />
      <Segment>
        <img src="../../../src/common/assets/icons/Logo.png" />
      </Segment>
      <Segment></Segment>
      <Segment></Segment>
      <Segment></Segment>
      <Segment></Segment>
      <Segment />
      <Segment>
        <p>내 작업</p>
      </Segment>
      <Segment>
        <p>마이페이지</p>
      </Segment>
      <Segment>
        <p>로그아웃</p>
      </Segment>
      <Segment />
      <Segment />
    </NavBar>
  );
};

export default NavigationBar;

const NavBar = styled.div`
  display: flex;
  flex-direction: row;
  background-color: ${colors.white};
  color: ${colors.primary};
  width: 100%;
  flex-basis: 100px;
  flex-shrink: 0;
  align-items: center;
  justify-content: center;
`;

const Segment = styled.div`
  flex: 1;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
`;
