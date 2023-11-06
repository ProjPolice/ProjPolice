import { Page, Container, Box, Header, HeaderText, InputBox, Column, Button } from './UserStyle';
import { colors } from '@assets/design/colors';


function Login() {
  return (
    <Page>
      <Container>
        <Box>
          <Header>
            <HeaderText>로그인</HeaderText>
          </Header>
          <Column height="30%">
          <InputBox
            topLeftRadius="8px"
            topRightRadius="8px"
            bottomLeftRadius="0px"
            bottomRightRadius="0px"
          >
            <input
                type="text"
                placeholder="아이디를 입력하세요"
                style={{
                  width: '100%',
                  height: '100%',
                  border: 'none',
                  outline: 'none',
                }}
              />
          </InputBox>
          <InputBox
            topLeftRadius="0px"
            topRightRadius="0px"
            bottomLeftRadius="8px"
            bottomRightRadius="8px"
          >
          <input
                type="password"
                placeholder="비밀번호를 입력하세요"
                style={{
                  width: '100%',
                  height: '100%',
                  border: 'none',
                  outline: 'none',
                }}
              />
          </InputBox>
          
        </Column>
        <Column height="10%">
          <Button>로그인</Button>
        </Column>

        </Box>
          <div style={{marginTop : '10px'}}>
            <p style={{ display: 'inline', color : colors.primary }} onClick={() => handlePClick('findid')}>아이디 찾기</p>
            <p style={{ display: 'inline', color : colors.primary }}> | </p>
            <p style={{ display: 'inline', color : colors.primary }} onClick={() => handlePClick('findpassword')}>비밀번호 찾기</p>
            <p style={{ display: 'inline', color : colors.primary }}> | </p>
            <p style={{ display: 'inline', color : colors.primary }} onClick={() => handlePClick('signup')}>회원가입</p>
          </div>
      </Container>
    </Page>
  );
}


export default Login;
