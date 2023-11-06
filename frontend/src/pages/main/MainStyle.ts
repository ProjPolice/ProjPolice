import { colors } from '@assets/design/colors';
import styled from '@emotion/styled';

export const HeaderBackground = styled.div`
  display: flex;
  width: 100%;
  height: 20%;
  background-color: ${colors.primary};
  align-self: center;
  justify-content: center;
`;

export const HeaderContainer = styled.div`
  display: flex;
  width: 70%;
  height: 100%;
  align-items: center;
  justify-content: space-between;
`;

export const HeaderBox = styled.div<{ backgroundColor?: string }>`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  background: linear-gradient(to right, ${(props) => props.backgroundColor} 10%, ${colors.light} 0%);
  border-radius: 10px;
  box-sizing: border-box;
  width: 30%;
  height: 70%;
  padding: 0% 5% 0% 5%;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.2);
`;

export const ProjectContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 70%;
  height: 40%;
  background-color: ${colors.white};
  justify-content: space-evenly;
`;

export const ProjectBoxContainer = styled.div`
  display: flex;
  justify-content: space-between;
  width: 100%;
  height: 80%;
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

export const TastkContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  width: 70%;
  height: 40%;
`;

export const TaskBoxContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  height: 100%;
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
  height: 20%;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.2);
`;

export const TextContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: end;
`;
