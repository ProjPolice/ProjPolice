import { Box, Container, Header, HeaderText, Page, Row } from './ProfileStyle';

function Profile() {
  return (
    <Page>
      <Container>
        <Header>
          <HeaderText>프로필 관리</HeaderText>
        </Header>
        <Row>
          <Box position="left">
            <h4>회원명</h4>
            <h6>{'나이키'}</h6>
          </Box>
          <Box position="right">
            <h4>프로필 사진</h4>
            <h6>{'사진'}</h6>
          </Box>
        </Row>
        <Row>
          <Box>
            <h4>이메일</h4>
            <h6>{'nikezz@icloud.com'}</h6>
          </Box>
        </Row>
        <Row>
          <Box>
            <h4>비밀번호</h4>
            <h6>{'비밀번호'}</h6>
          </Box>
        </Row>
      </Container>
    </Page>
  );
}

export default Profile;
