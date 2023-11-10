import { atom } from 'recoil';
import { persistAtom } from './persist';
import { Token } from '@interfaces/state';

export const tokenState = atom<Token>({
  key: 'tokenState',
  default: {
    accessToken: '',
    refreshToken: '',
  },
  effects_UNSTABLE: [persistAtom],
});
