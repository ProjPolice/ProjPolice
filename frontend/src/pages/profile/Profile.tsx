import { Box, BoxContext, BoxTitle, Container, Header, HeaderText, Page, Row } from './ProfileStyle';

function Profile() {
  return (
    <Page>
      <Container>
        <Header>
          <HeaderText>프로필 관리</HeaderText>
        </Header>
        <Row>
          <Box position="left">
            <BoxTitle>회원명</BoxTitle>
            <BoxContext>{'나이키'}</BoxContext>
          </Box>
          <Box position="right">
            <BoxTitle>프로필 사진</BoxTitle>
            <BoxContext>{'사진'}</BoxContext>
          </Box>
        </Row>
        <Row>
          <Box>
            <BoxTitle>이메일</BoxTitle>
            <BoxContext>{'nikezz@icloud.com'}</BoxContext>
          </Box>
        </Row>
        <Row>
          <Box>
            <BoxTitle>비밀번호</BoxTitle>
            <BoxContext>{'비밀번호'}</BoxContext>
          </Box>
        </Row>
      </Container>
    </Page>
  );
}

export default Profile;
