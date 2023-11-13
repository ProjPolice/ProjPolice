import { colors } from '@assets/design/colors';
import styled from '@emotion/styled';

export const BoardContainer = styled.div<{ height: string; background: string }>`
  display: flex;
  justify-content: space-evenly;
  width: 70%;
  margin: 0% 10% 0% 10%;
  background-color: ${(props) => props.background};
  height: ${(props) => props.height};
`;

export const Task = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background: ${colors.light};
  border-radius: 10px;
  box-sizing: border-box;
  width: 100%;
  height: 100%;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.2);
`;

export const TaskHeader = styled.div`
  width: 100%;
  height: 10%;
  background-color: ${colors.primary};
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
`;

export const TaskBody = styled.div`
  width: 98%;
  height: 90%;
  display: flex;
  gap: 1%;
`;

export const SectionTitle = styled.h4`
  font-weight: 100;
  margin-top: 5%;
`;

export const SectionContainer = styled.div`
  width: 100%;
  height: 100%;
  align-items: center;
`;

export const BoardSection = styled.div<{ backgroundColor: string }>`
  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: center;
  background: ${(props) => props.backgroundColor};
  border-radius: 10px;
  box-sizing: border-box;
  width: 100%;
  height: 92%;
  margin-left: 0.5%;
  margin-right: 0.5%;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.2);
`;

export const BoardBox = styled.div<{ backgroundColor: string; isDragging: boolean }>`
  display: flex;
  flex-direction: column;
  background: linear-gradient(to right, ${(props) => props.backgroundColor} 5%, ${colors.light} 0%);
  border-radius: 10px;
  width: 100%;
  margin-top: 3%;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.2);
  padding-left: 6%;
  padding-top: 3%;
  opacity: ${(props) => (props.isDragging ? 0.5 : 1)};
`;

export const TextContainer = styled.div`
  width: 70%;
  padding-bottom: 1%;
`;
