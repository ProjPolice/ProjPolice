import { instance } from '@api/http';
import { useRecoilValue } from 'recoil';
import { tokenState } from 'state/user';

export const useAxiosInterceptor = () => {
  const token = useRecoilValue(tokenState);
  instance.interceptors.request.use((config) => {
    const newConfig = { ...config };
    if (token.accessToken.length !== 0) {
      newConfig.headers.Authorization = `Bearer ${token.accessToken}`;
    }
    return newConfig;
  });
};
