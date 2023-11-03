import styled from '@emotion/styled';
import { colors } from '@assets/design/colors';
import { BoxProps } from './interfaces';

export const Page = styled.div`
  flex-grow: 1;
  display: flex;
  align-items: center;
  justify-content: center;
`;

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 50%;
  height: 50%;
  align-items: center;
`;

export const Header = styled.div`
  width: 100%;
  height: 25%;
  background-color: ${colors.primary};
  color: ${colors.white};
  border-radius: 10px 10px 0px 0px;
  display: flex;
  align-items: center;
`;

export const HeaderText = styled.h1`
  margin-left: 3%;
`;

export const Row = styled.div`
  width: 100%;
  height: 25%;
  display: flex;
  flex-direction: row;
`;

export const Box = styled.div<BoxProps>`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  border-color: ${colors.default};
  border-width: ${(props) =>
    props.position === 'left' ? '5px 2.5px 5px 5px' : props.position === 'right' ? '5px 5px 5px 2.5px' : '5px'};
  border-style: solid;
  padding-left: 5%;
  justify-content: space-evenly;
`;
