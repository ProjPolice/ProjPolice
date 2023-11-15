import { AppContainer, globalStyle } from '@assets/design/globalStyles';
import { Global } from '@emotion/react';
import NavigationBar from '@widgets/NavigationBar';
import { requestPermission } from 'firebase';
import { Outlet } from 'react-router-dom';

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
