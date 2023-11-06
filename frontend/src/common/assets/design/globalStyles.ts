import { css } from '@emotion/react';
import { colors } from './colors';

import bold from '../fonts/esamanru-Bold.ttf';
import medium from '../fonts/esamanru-Medium.ttf';
import light from '../fonts/esamanru-Light.ttf';
import styled from '@emotion/styled';

export const globalStyle = css`
  html {
    font-size: 62.5%;
  }

  @font-face {
    font-family: 'bold';
    src: url(${bold}) format('truetype');
  }

  @font-face {
    font-family: 'medium';
    src: url(${medium}) format('truetype');
  }

  @font-face {
    font-family: 'light';
    src: url(${light}) format('truetype');
  }

  h1 {
    font-family: 'light';
    font-size: 5.2rem;
    background-color: transparent;
    margin-top: 0;
    margin-bottom: 0;
  }

  h2 {
    font-family: 'light';
    font-size: 3.6rem;
    background-color: transparent;
    margin-top: 0;
    margin-bottom: 0;
  }

  h3 {
    font-family: 'light';
    font-size: 3rem;
    background-color: transparent;
    margin-top: 0;
    margin-bottom: 0;
  }

  h4 {
    font-family: 'light';
    font-size: 2.4rem;
    background-color: transparent;
    margin-top: 0;
    margin-bottom: 0;
  }

  h5 {
    font-family: 'medium';
    font-size: 1.8rem;
    background-color: transparent;
    margin-top: 0;
    margin-bottom: 0;
  }

  h6 {
    font-family: 'light';
    font-size: 1.8rem;
    background-color: transparent;
    margin-top: 0;
    margin-bottom: 0;
  }

  p {
    font-family: 'light';
    font-size: 1.8rem;
    background-color: transparent;
    margin-top: 0;
    margin-bottom: 0;
  }
`;

export const AppContainer = styled.div`
  width: 100vw;
  height: 100vh;
  display: flex;
  flex-direction: column;
  background-color: ${colors.white};
`;

export const Page = styled.div`
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;
