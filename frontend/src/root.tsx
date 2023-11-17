// import React from 'react';
import ReactDOM from 'react-dom/client';
import { RouterProvider } from 'react-router-dom';
import router from './Router.tsx';
import { RecoilRoot } from 'recoil';
import { Global } from '@emotion/react';
import { globalStyle } from '@assets/design/globalStyles.ts';

ReactDOM.createRoot(document.getElementById('root')!).render(
  <RecoilRoot>
    <Global styles={globalStyle} />
    <RouterProvider router={router} />
  </RecoilRoot>,
);
