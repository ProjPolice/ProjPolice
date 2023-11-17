import { AppContainer } from '@assets/design/globalStyles';
import NavigationBar from '@widgets/NavigationBar';
import { requestPermission } from 'firebase';
import { useEffect } from 'react';
import { Outlet, useNavigate } from 'react-router-dom';
import { useRecoilValue } from 'recoil';
import { accessToken } from 'state/user';

function App() {
  // 알림 권한
  requestPermission();

  const navigate = useNavigate();
  const token = useRecoilValue(accessToken);

  useEffect(() => {
    if (!token) {
      navigate('/intro');
    }
  });

  return (
    <AppContainer>
      <NavigationBar />
      <Outlet />
    </AppContainer>
  );
}

export default App;
