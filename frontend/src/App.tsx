import { AppContainer, globalStyle } from '@assets/design/globalStyles';
import { Global } from '@emotion/react';
import NavigationBar from '@widgets/NavigationBar';
import { Outlet } from 'react-router-dom';
import { requestPermission } from './firebase';

function App() {
  requestPermission();
  return (
    <AppContainer>
      <Global styles={globalStyle} />
      <NavigationBar />
      <Outlet />
    </AppContainer>
  );
}

export default App;
