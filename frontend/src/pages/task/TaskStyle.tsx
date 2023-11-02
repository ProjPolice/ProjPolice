import styled from '@emotion/styled';

export const Page = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100vh;
  align-items: center;
`;

export const BoardContainer = styled.div<{ height: string, background: string; }>`
  display: flex;
  justify-content: space-evenly;
  width: 80%;
  margin : 0% 10% 0% 10%;
  background-color: ${(props) => props.background};
  height: ${(props) => props.height};
`;

export const Task = styled.div<{ backgroundColor : string; }>`
  display: flex;
  justify-content: center;
  align-items: center;
  background: linear-gradient(to bottom, ${(props) => props.backgroundColor} 10%, #FAFAFA 0%);
  border-radius: 10px;
  box-sizing: border-box;
  width: 100%;
  height: 100%;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.2);
`;

export const BoardSection = styled.div<{ backgroundColor : string; }>`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  background: ${(props) => props.backgroundColor}
  border-radius: 10px;
  box-sizing: border-box;
  width: 100%;
  height: 100%;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.2);
`;