import { useRecoilValue } from 'recoil';
import { tokenState } from 'state/user';

export const getToken = () => {
  const token = useRecoilValue(tokenState);

  return token;
};
