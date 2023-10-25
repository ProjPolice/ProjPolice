import styled from '@emotion/styled';

export const Page = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100vh;
  align-items: center;
`;

export const Box = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border: 1px solid black;
  width: 30%;
  height: 80%;
`;

export const Container = styled.div<{ height: string }>`
  display: flex;
  justify-content: space-evenly;
  width: 80%;
  height: ${(props) => props.height};
`;
