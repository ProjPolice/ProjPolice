import styled from '@emotion/styled';
import { colors } from '@assets/design/colors';
import { ColumnProps, InputBoxProps } from '@interfaces/user';

export const UserPage = styled.div`
  width: 100vw;
  height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: ${colors.white};
`;

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 50%;
  height: 85%;
  align-items: center;
`;

export const Header = styled.div`
  height: 10%;
  margin-left: 4%;
  background-color: ${colors.white};
  color: ${colors.primary};
  display: flex;
`;

export const HeaderText = styled.h1`
  font-weight: 100;
`;

export const Box = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  border-color: ${colors.default};
  border-style: solid;
  justify-content: space-evenly;
  box-shadow: 4px 4px 8px rgba(0, 0, 0, 0.2);
  border-radius: 10px;
`;

export const Row = styled.div`
  width: 100%;
  height: 25%;
  display: flex;
  flex-direction: row;
`;

export const Column = styled.div<ColumnProps>`
  width: 100%;
  height: ${(props) => props.height};
  display: flex;
  align-items: center;
  flex-direction: Column;
`;

export const InputBox = styled.div<InputBoxProps>`
  width: 90%;
  height: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-top-left-radius: ${(props) => props.topLeftRadius || '4px'};
  border-top-right-radius: ${(props) => props.topRightRadius || '4px'};
  border-bottom-left-radius: ${(props) => props.bottomLeftRadius || '4px'};
  border-bottom-right-radius: ${(props) => props.bottomRightRadius || '4px'};
`;

export const Button = styled.button`
  background-color: ${colors.primary};
  color: ${colors.white};
  border: none;
  border-radius: 8px;
  cursor: pointer;

  @media (max-width: 768px) {
    padding: 5% 30%;
  }

  @media (min-width: 769px) {
    padding: 3% 30%;
  }
`;

export const LoginFooter = styled.div`
  display: flex;
  width: 35%;
  height: 10%;
  align-items: center;
  justify-content: space-between;
`;
