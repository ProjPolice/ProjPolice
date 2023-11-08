import { useState } from 'react';
import { Page } from '@assets/design/globalStyles';
import { Box, Container, Header, HeaderText, Row } from './ProfileStyle';

function Profile() {
  const [name, setName] = useState('회원명');
  const [photo, setPhoto] = useState('사진');
  const [email, setEmail] = useState('이메일');
  const [password, setPassword] = useState('비밀번호');
  const [isEditing, setIsEditing] = useState(false);

  const handleSaveProfile = () => {
    setIsEditing(false);
  };

  return (
    <Page>
      <Container>
        <Header>
          <HeaderText>프로필 관리</HeaderText>
          {isEditing ? (
            <p onClick={handleSaveProfile} style={{ cursor: 'pointer', marginRight: '5%' }}>
              저장
            </p>
          ) : (
            <p onClick={() => setIsEditing(true)} style={{ cursor: 'pointer', marginRight: '5%' }}>
              프로필수정
            </p>
          )}
        </Header>
        <Row>
          <Box position="left">
            <h4>회원명</h4>
            {isEditing ? (
              <input
                type="text"
                style={{ width: '50%', height: '20%' }}
                value={name}
                onChange={(e) => setName(e.target.value)}
              />
            ) : (
              <h6>{name}</h6>
            )}
          </Box>
          <Box position="right">
            <h4>프로필 사진</h4>
            {isEditing ? (
              <input type="text" value={photo} onChange={(e) => setPhoto(e.target.value)} />
            ) : (
              <h6>{photo}</h6>
            )}
          </Box>
        </Row>
        <Row>
          <Box>
            <h4>이메일</h4>
            {isEditing ? (
              <input
                type="text"
                style={{ width: '50%', height: '20%' }}
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
            ) : (
              <h6>{email}</h6>
            )}
          </Box>
        </Row>
        <Row>
          <Box>
            <h4>비밀번호</h4>
            {isEditing ? (
              <input
                type="text"
                style={{ width: '50%', height: '20%' }}
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            ) : (
              <h6>{password}</h6>
            )}
          </Box>
        </Row>
      </Container>
    </Page>
  );
}

export default Profile;
