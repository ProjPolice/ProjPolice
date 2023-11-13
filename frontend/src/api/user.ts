import { CommonResponse, http } from './http';

export default {
  data: () => http.get<DataResponse>('users'),
  login: (data: LoginRequest) => http.post<LoginResponse>('users', data),
  modify: (data: ModifyRequest) => http.patch<ModifyResponse>('users', data),
  signup: (data: SignupRequest) =>
    http.post<SignupResponse>('users/join', data, { headers: { 'Content-Type': 'multipart/form-data' } }),
  tasks: () => http.get<TaskResponse>('users/tasks'),
  projects: () => http.get<ProjectResponse>('users/projects'),
};

interface SignupRequest {
  name: string;
  email: string;
  image?: File;
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
  image?: File;
}

interface ModifyResponse extends CommonResponse {
  data: {
    id: number;
    name: string;
    email: string;
    image: string;
  };
}

export interface TaskItem {
  id: number;
  name: string;
  startDate: string;
  endDate: string;
  epic: {
    id: number;
    name: string;
  };
  project: {
    id: number;
    name: string;
  };
  file: {
    id: number;
    name: string;
  };
  userId: number;
}

export interface TaskData {
  todo: TaskItem[];
  inProgress: TaskItem[];
  done: TaskItem[];
}

interface TaskResponse extends CommonResponse {
  data: TaskData;
}

export interface Projects {
  id: number;
  name: string;
  description: string;
}

interface ProjectResponse extends CommonResponse {
  data: {
    projects: Projects[];
  };
  pages: number;
  numOfRows: number;
}
