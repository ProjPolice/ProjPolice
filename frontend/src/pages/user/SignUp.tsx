import { ChangeEvent, useState } from 'react';
import { Page } from '@assets/design/globalStyles';
import { Header, HeaderText, Container, Box, Column, InputBox, Button } from './UserStyle';
import user from '@api/user';

function SignUp() {
  const [name, setName] = useState('');
  const [password, setPassword] = useState('');
  const [passwordConfirmation, setPasswordConfirmation] = useState('');
  const [email, setEmail] = useState('');
  const [image, setImage] = useState<FormData>();
  const [imageSrc, setImageSrc] = useState<string>();

  const handleChangeName = (event: ChangeEvent<HTMLInputElement>) => {
    setName(event.target.value);
  };

  const handleChangePassword = (event: ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
  };

  const handleChangePasswordConfirmation = (event: ChangeEvent<HTMLInputElement>) => {
    setPasswordConfirmation(event.target.value);
  };

  const handleChangeEmail = (event: ChangeEvent<HTMLInputElement>) => {
    setEmail(event.target.value);
  };

  const handleImageUpload = (event: ChangeEvent<HTMLInputElement>) => {
    const files = event.target.files;
    if (files) {
      const formData = new FormData();
      const reader = new FileReader();
      reader.onload = () => {
        const result = reader.result as string;
        setImageSrc(result);
      };
      formData.append('file', files[0]);
      reader.readAsDataURL(files[0]);
      setImage(formData);
    }
  };

  const submitSignup = () => {
    if (password === passwordConfirmation) {
      const data = {
        name: name,
        email: email,
        image: image,
        password: password,
      };
      user.signup(data).then((response) => {
        console.log(response);
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
                  onChange={handleImageUpload}
                />
              </div>
            )}
          </Column>
          <Column height="40%">
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
                value={name}
                onChange={handleChangeName}
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
                onChange={handleChangePassword}
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
                onChange={handleChangePasswordConfirmation}
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
                onChange={handleChangeEmail}
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
