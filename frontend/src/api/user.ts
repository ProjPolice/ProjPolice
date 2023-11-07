import { CommonResponse, http } from './http';

export default {
  signup: (data: SignupRequest) => http.post<SignupResponse>('users/join', { data }),
};

interface SignupRequest {
  name: string;
  email: string;
  image?: FormData;
  password: string;
}

interface SignupResponse extends CommonResponse {
  data: {
    id: number;
  };
}
