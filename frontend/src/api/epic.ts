import { CommonResponse, http } from './http';

export default {
  data: () => http.get<DataResponse>('epics'),
  create: (data: CreateRequest) => http.post<CreateResponse>('epics', { data }),
  tododata: (epicsId: number) => http.get<TodoDataResponse>(`epics/${epicsId}`),
  tododelete: (epicsId: number) => http.delete<TodoDeleteResponse>(`epics/${epicsId}`),
  todomodify: (epicsId: number, data: TodoModifyRequest) =>
    http.patch<TodoModifyResponse>(`epics/${epicsId}`, { data }),
};

interface DataResponse extends CommonResponse {
  data: [
    {
      id: number;
      name: string;
      startDate: string;
      endDate: string;
      tasks: [
        {
          id: number;
          name: string;
          startDate: string;
          endDate: string;
        },
      ];
    },
  ];
}

interface CreateRequest {
  name: string;
  description: string;
  startDate: string;
  endDate: string;
  projectId: number;
}

interface CreateResponse extends CommonResponse {
  data: {
    id: number;
    name: string;
    description: string;
    startDate: string;
    endDate: string;
    projectId: number;
  };
}

interface TodoDataResponse extends CommonResponse {
  data: {
    id: number;
    name: string;
    description: string;
    startDate: string;
    endDate: string;
    projectId: number;
  };
}

interface TodoDeleteResponse extends CommonResponse {
  data: {
    id: number;
  };
}

interface TodoModifyRequest {
  name: string;
  description: string;
  startDate: string;
  endDate: string;
}

interface TodoModifyResponse extends CommonResponse {
  data: {
    id: number;
    name: string;
    description: string;
    startDate: string;
    endDate: string;
    projectId: number;
  };
}
