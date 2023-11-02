import { globalStyle } from '@assets/design/globalStyles';
import { Global } from '@emotion/react';
import { Outlet } from 'react-router-dom';

function App() {
  return (
    <div>
      <Global styles={globalStyle} />
      <Outlet />
    </div>
  );
}

export default App;
