import React, { useState } from 'react';
import { Header, HeaderText, Page, Container, Box, Column, InputBox, Button } from './UserStyle';

function SignUp() {
  const [image, setImage] = useState(null);

  const handleImageUpload = (e) => {
    const file = e.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        setImage(reader.result);
      };
      reader.readAsDataURL(file);
    }
  };

  return (
    <Page>
      <Container>
        <Box>
          <Header><HeaderText>회원가입</HeaderText></Header>
          <Column height="40%">
            {image ? (
              <img
                src={image}
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
            bottomLeftRadius="0px"
            bottomRightRadius="0px"
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
          <InputBox
            topLeftRadius="0px"
            topRightRadius="0px"
            bottomLeftRadius="8px"
            bottomRightRadius="8px"
          >
            <input
                type="text"
                placeholder="이메일을 입력하세요"
                style={{
                  width: '100%',
                  height: '100%',
                  border: 'none',
                  outline: 'none',
                }}
              />
          </InputBox>
        </Column>
        </Box>
          <div style={{marginTop : '5%'}}></div>
          <Button>회원가입</Button>
      </Container>
    </Page>
  );
}

export default SignUp;