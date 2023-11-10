import { CommonResponse, http } from './http';

export default {
  data: () => http.get<DataResponse>('users'),
  login: (data: LoginRequest) => http.post<LoginResponse>('users', data),
  modify: (data: ModifyRequest) => http.patch<ModifyResponse>('users', data),
  signup: (data: SignupRequest) => http.post<SignupResponse>('users/join', data),
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

interface LoginRequest {
  email: string;
  password: string;
}

interface LoginResponse extends CommonResponse {
  data: {
    accessToken: string;
    refreshToken: string;
  };
}

interface DataResponse extends CommonResponse {
  data: {
    id: number;
    name: string;
    email: string;
    image: string;
  };
}

interface ModifyRequest {
  name: string;
  email: string;
  image: string;
}

interface ModifyResponse extends CommonResponse {
  data: {
    id: number;
    name: string;
    email: string;
    image: string;
  };
}
