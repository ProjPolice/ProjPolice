import { Page } from '@assets/design/globalStyles';
import { Header, HeaderText, Container, Box, Column, InputBox, Button } from './UserStyle';
import { useTextInput } from 'common/hooks/useTextInput';

import user from '@api/user';
import { useImageInput } from 'common/hooks/useFileInput';
import { useNavigate } from 'react-router-dom';

function SignUp() {
  const navigate = useNavigate();
  const [name, handleName] = useTextInput();
  const [password, handlePassword] = useTextInput();
  const [passwordConfirmation, handlePasswordConfirmation] = useTextInput();
  const [email, handleEmail] = useTextInput();
  const [image, handleImage, imageSrc] = useImageInput();

  const submitSignup = () => {
    if (password === passwordConfirmation) {
      const data = {
        name: name,
        email: email,
        image: image,
        password: password,
      };
      user
        .signup(data)
        .then((response) => {
          console.log(response);
          navigate('/');
        })
        .catch((error) => {
          console.log(error);
        });
    } else {
      alert('비밀번호와 비밀번호 확인이 서로 다릅니다.');
    }
  };

  return (
    <Page>
      <Container>
        <Box>
          <Header>
            <HeaderText>회원가입</HeaderText>
          </Header>
          <Column height="40%">
            {image ? (
              <img
                src={imageSrc}
                alt="Selected Image"
                style={{
                  maxWidth: '100%',
                  maxHeight: '100%',
                  objectFit: 'contain',
                }}
              />
            ) : (
              <div id="imageButtonContainer">
                <label htmlFor="imageInput" className="imageInputLabel">
                  이미지 추가
                </label>
                <input
                  type="file"
                  id="imageInput"
                  accept="image/*"
                  style={{ display: 'none' }}
                  onChange={handleImage}
                />
              </div>
            )}
          </Column>
          <Column height="40%">
            <InputBox topLeftRadius="8px" topRightRadius="8px" bottomLeftRadius="0px" bottomRightRadius="0px">
              <input
                type="text"
                placeholder="이름을 입력하세요"
                style={{
                  width: '100%',
                  height: '100%',
                  border: 'none',
                  outline: 'none',
                }}
                value={name}
                onChange={handleName}
              />
            </InputBox>
            <InputBox topLeftRadius="0px" topRightRadius="0px" bottomLeftRadius="8px" bottomRightRadius="8px">
              <input
                type="text"
                placeholder="이메일을 입력하세요"
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
            <InputBox topLeftRadius="0px" topRightRadius="0px" bottomLeftRadius="0px" bottomRightRadius="0px">
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
            <InputBox topLeftRadius="0px" topRightRadius="0px" bottomLeftRadius="0px" bottomRightRadius="0px">
              <input
                type="password"
                placeholder="비밀번호를 한번 더 입력하세요"
                style={{
                  width: '100%',
                  height: '100%',
                  border: 'none',
                  outline: 'none',
                }}
                value={passwordConfirmation}
                onChange={handlePasswordConfirmation}
              />
            </InputBox>
          </Column>
        </Box>
        <div style={{ marginTop: '5%' }}></div>
        <Button onClick={submitSignup}>회원가입</Button>
      </Container>
    </Page>
  );
}

export default SignUp;
