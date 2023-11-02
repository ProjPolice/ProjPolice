import styled from '@emotion/styled';

export const Page = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100vh;
  align-items: center;
`;

export const Box = styled.div<{ backgroundColor : string; }>`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  background: linear-gradient(to right, ${(props) => props.backgroundColor} 10%, #FAFAFA 0%);
  border-radius: 10px;
  box-sizing: border-box;
  width: 30%;
  height: 80%;
  margin-Top: 1%;
  padding: 0% 5% 0% 5%;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.2);
`;

export const ProjectBox = styled.div<{ backgroundColor : string; }>`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background: linear-gradient(to bottom, ${(props) => props.backgroundColor} 10%, #FAFAFA 0%);
  border-radius: 10px;
  box-sizing: border-box;
  width: 30%;
  height: 100%;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.2);
`;

export const ProjectContainer = styled.div<{ height: string, background: string; }>`
  display: flex;
  justify-content: space-evenly;
  width: 80%;
  margin : 0% 10% 0% 10%;
  background-color: ${(props) => props.background};
  height: ${(props) => props.height};
`;


export const Container = styled.div<{ height: string, background: string; }>`
  display: flex;
  justify-content: space-evenly;
  width: 80%;
  margin : 0% 10% 0% 10%;
  background-color: ${(props) => props.background};
  height: ${(props) => props.height};
`;

export const NavBar = styled.div`
  display: flex;  
  background-color: #FFFFFF;
  color: #2F79D9;
  height: 10%;
  width : 80%;
  flex-direction: row;
  align-items: center;
`;

export const Segment = styled.div`
  flex: 1;
`;


export const TastkContainer = styled.div<{ height: string, background: string; }>`
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  width: 80vw;
  background-color: ${(props) => props.background};
  height: ${(props) => props.height};
`;

export const TaskBox = styled.div<{ backgroundColor : string; }>`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  background: linear-gradient(to bottom, ${(props) => props.backgroundColor} 10%, #FAFAFA 0%);
  border-radius: 10px;
  box-sizing: border-box;
  width: 100%;
  height: 100%;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.2);
`;

