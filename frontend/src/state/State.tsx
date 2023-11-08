import { atom } from 'recoil';

export const userState = atom({
  key: 'tokenState',
  default: {
    token: '',
  },
});
