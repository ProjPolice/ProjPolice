import { colors } from '@assets/design/colors';
import styled from '@emotion/styled';

export const Container = styled.div<{ width: string; height: string }>`
  position: absolute;
  display: flex;
  flex-direction: column;
  align-items: center;
  overflow: hidden;
  background-color: ${colors.light};
  width: ${(props) => props.width};
  height: ${(props) => props.height};
  border-radius: 10px;
  border: 1px solid ${colors.default};
  box-sizing: border-box;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.2);
  z-index: 2;
`;

export const ContainerNav = styled.div<{ height: string; background: string }>`
  display: flex;
  background-color: ${(props) => props.background};
  height: ${(props) => props.height};
  width: 100%;
`;

export const ContainerContent = styled.div<{ width: string; height: string; background: string }>`
  display: flex;
  flex-direction: column;
  height: ${(props) => props.height};
  width: ${(props) => props.width};
  /* justify-content: center; */
  /* align-items: center; */
  margin: 0% 15% 0%;
  background-color: ${(props) => props.background};
`;

export const ContentItem = styled.div<{ width: string; height: string; justifycontent: string }>`
  display: flex;
  height: ${(props) => props.height};
  width: ${(props) => props.width};
  justify-content: ${(props) => props.justifycontent};
  align-items: center;
  margin-bottom: 2px;
`;

export const ColumnItem = styled.div<{ width: string; height: string }>`
  display: flex;
  flex-direction: column;
  height: ${(props) => props.height};
  width: ${(props) => props.width};
  /* justify-content: center; */
  /* align-items: center; */
  margin: 5% 3px 5%;
`;

export const ContentName = styled.div<{ width: string }>`
  display: flex;
  width: ${(props) => props.width};
  /* margin: 0% 20px 0% 0%; */
  /* justify-content: space-between; */
  align-items: center;
`;

export const Segment = styled.div<{ width: string; height: string }>`
  display: flex;
  height: ${(props) => props.height};
  width: ${(props) => props.width};
  justify-content: space-evenly;
  align-items: center;
`;

export const InputBox = styled.div<{ width: string; flexgrow: string }>`
  display: flex;
  flex-grow: ${(props) => props.flexgrow};
  width: ${(props) => props.width};
  height: 80%;
  padding: 0% 2% 0% 2%;
  border: 1px solid #ccc;
  border-top-left-radius: 4px;
  border-top-right-radius: 4px;
  border-bottom-left-radius: 4px;
  border-bottom-right-radius: 4px;
  background-color: #ffffff;
`;
