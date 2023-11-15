import { atom } from 'recoil';
import { persistAtom } from './persist';

export const accessToken = atom({
  key: 'accessToken',
  default: '',
  effects_UNSTABLE: [persistAtom],
});

export const refreshToken = atom({
  key: 'refreshToken',
  default: '',
  effects_UNSTABLE: [persistAtom],
});
