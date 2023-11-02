import { css } from '@emotion/react';
import { colors } from './colors';

import bold from '../fonts/esamanru-Bold.ttf';
import medium from '../fonts/esamanru-Medium.ttf';
import light from '../fonts/esamanru-Light.ttf';

export const globalStyle = css`
  * {
    background-color: ${colors.white};
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
    font-family: 'bold';
    font-size: 42px;
    background-color: transparent;
    margin-top: 0;
    margin-bottom: 0;
  }

  h2 {
    font-family: 'bold';
    font-size: 36px;
    background-color: transparent;
    margin-top: 0;
    margin-bottom: 0;
  }

  h3 {
    font-family: 'bold';
    font-size: 30px;
    background-color: transparent;
    margin-top: 0;
    margin-bottom: 0;
  }

  h4 {
    font-family: 'bold';
    font-size: 24px;
    background-color: transparent;
    margin-top: 0;
    margin-bottom: 0;
  }

  h5 {
    font-family: 'bold';
    font-size: 18px;
    background-color: transparent;
    margin-top: 0;
    margin-bottom: 0;
  }

  h6 {
    font-family: 'medium';
    font-size: 18px;
    background-color: transparent;
    margin-top: 0;
    margin-bottom: 0;
  }

  p {
    font-family: 'light';
    font-size: 18px;
    background-color: transparent;
    margin-top: 0;
    margin-bottom: 0;
  }
`;
