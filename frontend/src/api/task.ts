import { CommonResponse, http } from './http';

export default {
  create: (data: CreateRequest) => http.post<CreateResponse>('tasks', { data }),
  modify: (taskId: number, data: ModifyRequest) => http.patch<ModifyResponse>(`tasks/${taskId}`, { data }),
};

interface CreateRequest {
  name: string;
  description: string;
  startDate: string;
  endDate: string;
  status: string;
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
