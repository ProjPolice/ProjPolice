import { colors } from '@assets/design/colors';
import styled from '@emotion/styled';

export const CenterContainer = styled.div<{ background: string }>`
  flex-direction: column;
  width: 70%;
  height: 100vh;
  align-items: center;
  background-color: ${(props) => props.background};
`;

export const Container = styled.div<{ width: string; height: string; background: string; flexdirection: string }>`
  display: flex;
  flex-direction: ${(props) => props.flexdirection};
  justify-content: center;
  align-items: center;
  /* overflow: auto; */
  /* padding: 5%; */
  width: ${(props) => props.width};
  height: ${(props) => props.height};
  background-color: ${(props) => props.background};
  box-sizing: border-box;
  /* border: 1px solid ${colors.default}; */
  /* box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.2); */
`;

export const Photo = styled.div<{
  width: string;
  // height: string;
  background: string;
  imgurl: string;
}>`
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
  aspect-ratio: 1/1;
  /* background-color: ${(props) => props.background} !important; */

  width: ${(props) => props.width};
  background: url(${(props) => props.imgurl}) no-repeat center center;
  background-size: 100% auto;
`;

export const Segment = styled.div`
  flex: 1;
`;

export const Button = styled.div<{ width: string; height: string; fontsize: string }>`
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
  margin: 5%;
  background-color: ${colors.primary};
  width: ${(props) => props.width};
  height: ${(props) => props.height};
  border-radius: 5px;
  color: ${colors.white};
  font-family: 'light';
  font-size: ${(props) => props.fontsize};
  box-sizing: border-box;
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);

  transition: all 0.3s ease;
  &:hover {
    background-color: #2080d1;
    cursor: pointer;
  }
`;

export const Ptext = styled.p`
  line-height: 1.4;
  font-size: 1.6rem;
  white-space: pre-line;
`;
