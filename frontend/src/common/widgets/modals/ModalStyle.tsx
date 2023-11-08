import { colors } from '@assets/design/colors';
import styled from '@emotion/styled';

export const Container = styled.div<{ width: string; height: string }>`
  position: absolute;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  overflow: hidden;
  background-color: ${colors.white};
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

export const ContentItem = styled.div<{ width: string; height: string }>`
  display: flex;
  height: ${(props) => props.height};
  width: ${(props) => props.width};
  justify-content: space-between;
  /* align-items: center; */
`;

export const Segment = styled.div<{ width: string; height: string }>`
  display: flex;
  height: ${(props) => props.height};
  width: ${(props) => props.width};
`;
