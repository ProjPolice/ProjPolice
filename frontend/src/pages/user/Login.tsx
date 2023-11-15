import { Page, link } from '@assets/design/globalStyles';
import { Container, Box, Header, HeaderText, InputBox, Column, Button, LoginFooter } from './UserStyle';
import { colors } from '@assets/design/colors';
import { Link, useNavigate } from 'react-router-dom';
import { useTextInput } from 'common/hooks/useTextInput';
import user from '@api/user';
import { useSetRecoilState } from 'recoil';
import { accessToken, refreshToken } from 'state/user';

function Login() {
  const setAccess = useSetRecoilState(accessToken);
  const setRefresh = useSetRecoilState(refreshToken);
  const navigate = useNavigate();
  const findId = () => {};
  const findPassword = () => {};

  const [email, handleEmail] = useTextInput();
  const [password, handlePassword] = useTextInput();

  const submitLogin = () => {
    const data = {
      email: email,
      password: password,
      token: localStorage.getItem('FirebaseToken'),
    };
    user
      .login(data)
      .then((response) => {
        if (response.code === 200) {
          setAccess(response.data.accessToken);
          setRefresh(response.data.refreshToken);
          navigate('/');
        } else if (response.code === 1002) {
          alert('로그인에 실패하였습니다.');
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <Page>
      <Container>
        <Box>
          <Header>
            <HeaderText>로그인</HeaderText>
          </Header>
          <Column height="30%">
            <InputBox topLeftRadius="8px" topRightRadius="8px" bottomLeftRadius="0px" bottomRightRadius="0px">
              <input
                type="text"
                placeholder="아이디를 입력하세요"
                style={{
                  width: '100%',
                  height: '100%',
                  border: 'none',
                  outline: 'none',
                }}
                value={email}
                onChange={handleEmail}
              />
            </InputBox>
            <InputBox topLeftRadius="0px" topRightRadius="0px" bottomLeftRadius="8px" bottomRightRadius="8px">
              <input
                type="password"
                placeholder="비밀번호를 입력하세요"
                style={{
                  width: '100%',
                  height: '100%',
                  border: 'none',
                  outline: 'none',
                }}
                value={password}
                onChange={handlePassword}
              />
            </InputBox>
          </Column>
          <Column height="10%">
            <Button onClick={submitLogin}>로그인</Button>
          </Column>
        </Box>
        <LoginFooter>
          <p style={{ display: 'inline', color: colors.primary }} onClick={findId}>
            아이디 찾기
          </p>
          <p style={{ display: 'inline', color: colors.primary }}> | </p>
          <p style={{ display: 'inline', color: colors.primary }} onClick={findPassword}>
            비밀번호 찾기
          </p>
          <p style={{ display: 'inline', color: colors.primary }}> | </p>
          <Link to="/signup" css={link}>
            <p>회원가입</p>
          </Link>
        </LoginFooter>
      </Container>
    </Page>
  );
}

export default Login;
