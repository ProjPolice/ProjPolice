import styled from '@emotion/styled';
import { colors } from '@assets/design/colors';
import { BoxProps } from './interfaces';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 50%;
  height: 85%;
  align-items: center;
`;

export const Header = styled.div`
  width: 100%;
  height: 15%;
  background-color: ${colors.primary};
  color: ${colors.white};
  border-radius: 10px 10px 0px 0px;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

export const HeaderText = styled.h1`
  margin-left: 3%;
  font-weight: 100;
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
    props.type === 'name'
      ? '5px 2.5px 5px 5px'
      : props.type === 'image'
      ? '5px 5px 5px 2.5px'
      : props.type === 'email'
      ? '0px 5px 0px 5px'
      : '5px'};
  border-style: solid;
  padding-left: 5%;
  justify-content: space-evenly;
`;
