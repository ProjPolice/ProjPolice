import { CommonResponse, http } from './http';

export default {
  create: (data: CreateRequest) => http.post<CreateResponse>('tasks', data),
  modify: (taskId: number, data: ModifyRequest) => http.patch<ModifyResponse>(`tasks/${taskId}`, data),
  data: (projectId: number, epicId: number) =>
    http.get<TasksResponse>(`tasks?project_Id=${projectId}&epic_id=${epicId}`),
};

interface CreateRequest {
  name: string;
  description: string;
  startDate: string;
  endDate: string;
  status?: string;
  userId: number;
  epicId: number;
}

interface CreateResponse extends CommonResponse {
  data: {
    id: number;
    name: string;
    description: string;
    startDate: string;
    endDate: string;
    status: string;
  };
}

interface ModifyRequest {
  name: string;
  description: string;
  startDate: string;
  endDate: string;
  status: string;
  userId: number;
  epicId: number;
}

interface ModifyResponse extends CommonResponse {
  data: {
    id: number;
    name: string;
    description: string;
    startDate: string;
    endDate: string;
    status: string;
    user: {
      id: number;
      name: string;
      image: string;
    };
    epic: {
      id: number;
      name: string;
    };
  };
}

export interface TasksData {
  id: number;
  name: string;
  startDate: string;
  endDate: string;
  status?: string;
  member?: {
    id: number;
    name: string;
    image: string;
  };
  epic?: {
    id: number;
    name: string;
  };
  file?: {
    name: string;
    uuid: string;
  };
}

interface TasksResponse extends CommonResponse {
  data: TasksData[];
}
