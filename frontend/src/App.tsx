import { AppContainer, globalStyle } from '@assets/design/globalStyles';
import { Global } from '@emotion/react';
import NavigationBar from '@widgets/NavigationBar';
import { useAxiosInterceptor } from 'common/hooks/useAxiosInterceptor';
import { Outlet } from 'react-router-dom';

function App() {
  useAxiosInterceptor();

  return (
    <AppContainer>
      <Global styles={globalStyle} />
      <NavigationBar />
      <Outlet />
    </AppContainer>
  );
}

export default App;
