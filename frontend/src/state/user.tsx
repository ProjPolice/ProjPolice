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

export const userIdState = atom({
  key: 'userIdState',
  default: -1,
  effects_UNSTABLE: [persistAtom],
});

export const countState = atom({
  key: 'countState',
  default: {
    project: 0,
    epic: 0,
    task: 0,
  },
});
