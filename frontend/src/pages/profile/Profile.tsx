import { useEffect, useState } from 'react';
import { Page } from '@assets/design/globalStyles';
import { Box, Container, Header, HeaderText, Row } from './ProfileStyle';
import user from '@api/user';
import { useTextInput } from 'common/hooks/useTextInput';
import { useImageInput } from 'common/hooks/useFileInput';

function Profile() {
  const [name, handleName, setName] = useTextInput();
  const [, handleImage, imageSrc, setImageSrc] = useImageInput();
  const [email, handleEmail, setEmail] = useTextInput();
  const [password, handlePassword] = useTextInput();
  const [isEditing, setIsEditing] = useState(false);

  const handleSaveProfile = () => {
    setIsEditing(false);
  };

  useEffect(() => {
    user.data().then((response) => {
      setName(response.data.name);
      setImageSrc(response.data.image);
      setEmail(response.data.email);
    });
  }, []);

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
              <div>
                <input type="file" onChange={handleImage} />
                <></>
              </div>
            ) : (
              <img src={imageSrc} />
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
