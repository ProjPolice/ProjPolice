import { colors } from '@assets/design/colors';
import { link } from '@assets/design/globalStyles';
import styled from '@emotion/styled';

import { Link } from 'react-router-dom';
import { useRecoilValue, useResetRecoilState } from 'recoil';

import Logo from '@assets/images/ProjPoliceIcon.png';
import { tokenState } from 'state/user';

const NavigationBar = () => {
  const token = useRecoilValue(tokenState);

  const logout = () => {
    useResetRecoilState(tokenState);
  };

  return (
    <NavBar>
      <LogoContainer>
        <Link to={'/'}>
          <img src={Logo} height={'55px'} />
        </Link>
      </LogoContainer>
      <ToolBox>
        <Segment>
          <Link to={'/task'} css={link}>
            <p>내 작업</p>
          </Link>
        </Segment>
        <Segment>
          <Link to={'/profile'} css={link}>
            <p>마이페이지</p>
          </Link>
        </Segment>
        <Segment>
          <p>알림</p>
        </Segment>
        {token.accessToken === '' ? (
          <Segment>
            <Link to={'/login'} css={link}>
              <p>로그인</p>
            </Link>
          </Segment>
        ) : (
          <Segment>
            <Link to={'/'} css={link} onClick={logout}>
              <p>로그아웃</p>
            </Link>
          </Segment>
        )}
      </ToolBox>
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
  justify-content: space-around;
`;

const ToolBox = styled.div`
  width: 20%;
  display: flex;
  justify-content: space-between;
`;

const LogoContainer = styled.div`
  width: 15%;
`;

const Segment = styled.div`
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
`;
