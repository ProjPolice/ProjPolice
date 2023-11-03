import { colors } from '@assets/design/colors';
import styled from '@emotion/styled';

export const Page = styled.div`
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export const Box = styled.div<{ backgroundColor?: string }>`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  background: linear-gradient(to right, ${(props) => props.backgroundColor} 10%, ${colors.light} 0%);
  border-radius: 10px;
  box-sizing: border-box;
  width: 30%;
  height: 80%;
  margin-top: 1%;
  padding: 0% 5% 0% 5%;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.2);
`;

export const ProjectBox = styled.div<{ backgroundColor: string }>`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background: linear-gradient(to bottom, ${(props) => props.backgroundColor} 10%, ${colors.light} 0%);
  border-radius: 10px;
  box-sizing: border-box;
  width: 30%;
  height: 100%;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.2);
`;

export const ProjectContainer = styled.div<{ height: string; background: string }>`
  display: flex;
  justify-content: space-evenly;
  width: 80%;
  margin: 0% 10% 0% 10%;
  background-color: ${(props) => props.background};
  height: ${(props) => props.height};
`;

export const Container = styled.div<{ height: string; background: string }>`
  display: flex;
  justify-content: space-evenly;
  width: 80%;
  margin: 0% 10% 0% 10%;
  background-color: ${(props) => props.background};
  height: ${(props) => props.height};
`;

export const TastkContainer = styled.div<{ height: string; background: string }>`
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  width: 80vw;
  background-color: ${(props) => props.background};
  height: ${(props) => props.height};
`;

export const TaskBox = styled.div<{ backgroundColor: string }>`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  background: linear-gradient(to bottom, ${(props) => props.backgroundColor} 10%, ${colors.light} 0%);
  border-radius: 10px;
  box-sizing: border-box;
  width: 100%;
  height: 100%;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.2);
`;
