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

export const LeftLinedBox = styled.div<{ backgroundColor?: string; width?: string; height?: string }>`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  background: linear-gradient(to right, ${(props) => props.backgroundColor} 10%, ${colors.light} 0%);
  border-radius: 10px;
  box-sizing: border-box;
  width: ${(props) => (props.width ? props.width : '30%')};
  height: ${(props) => (props.height ? props.height : '70%')};
  padding: 0% 5% 0% 5%;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.2);
`;

export const ProjectContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 70%;
  height: 50%;
  background-color: ${colors.white};
  justify-content: space-evenly;
`;

export const ProjectBoxContainer = styled.div`
  display: flex;
  width: 100%;
  height: 70%;
  padding: 1%;
  gap: 4%;
`;

export const ProjectBox = styled.div<{ backgroundColor: string }>`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  background: linear-gradient(to bottom, ${(props) => props.backgroundColor} 10%, ${colors.light} 0%);
  border-radius: 10px;
  box-sizing: border-box;
  width: 30%;
  height: 100%;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.2);
  padding-top: 4%;
`;

export const ProjectBoxTitle = styled.div`
  display: flex;
  text-align: start;
  width: 90%;
  height: 10%;
`;

export const ProjectBoxMember = styled.div`
  display: flex;
  align-items: center;
  width: 90%;
  height: 15%;
  gap: 3%;
`;

export const ProjectBoxTask = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 90%;
  height: 70%;
  flex-wrap: wrap;
  padding-bottom: 1%;
`;

export const TaskContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  width: 70%;
  height: 50%;
  padding-bottom: 1%;
`;

export const TaskBoxContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 60%;
  gap: 2%;
`;

export const TaskBox = styled.div<{ backgroundColor: string }>`
  display: flex;
  flex-direction: column;
  background: linear-gradient(to bottom, ${(props) => props.backgroundColor} 15%, ${colors.light} 0%);
  border-radius: 10px;
  box-sizing: border-box;
  width: 100%;
  height: 100%;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.2);
`;

export const TextContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: end;
`;

export const TaskListHeader = styled.div`
  display: flex;
  justify-content: space-around;
  align-items: center;
  height: 15%;
`;

export const TaskListBody = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  height: 85%;
  overflow-y: auto;
`;

export const TaskListItem = styled.div`
  display: flex;
  justify-content: space-around;
`;
