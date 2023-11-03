import styled from '@emotion/styled';

export function Test() {
  return (
    <Container>
      <h1>안녕하세요</h1>
      <h2>안녕하세요</h2>
      <h3>안녕하세요</h3>
      <h4>안녕하세요</h4>
      <h5>안녕하세요</h5>
      <h6>안녕하세요</h6>
      <p>안녕하세요</p>
    </Container>
  );
}

const Container = styled.div`
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  gap: 30px;
`;
