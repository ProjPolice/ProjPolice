import { CommonResponse, http } from './http';

export default {
  create: (data: CreateRequest) => http.post<CreateResponse>('projects', data),
  memberdata: (projectId: number) => http.get<MemberDataResponse>(`projects/${projectId}/users`),
  memberCreate: (projectId: number, data: MemberCreateRequest) =>
    http.post<MemberCreateResponse>(`projects/${projectId}/users`, data),
  memberDelete: (projectId: number, userId: number) =>
    http.post<MemberDeleteResponse>(`projects/${projectId}/users/${userId}`),
  delete: (projectId: number) => http.delete<DeleteResponse>(`/projects/${projectId}`),
  modify: (projectId: number, data: ModifyRequest) => http.patch<ModifyResponse>(`projects/${projectId}`, data),
  data: (projectId: number) => http.get<DataResponse>(`projects/${projectId}`),
};

interface CreateRequest {
  name: string;
  description: string;
  startDate: string;
  endDate: string;
}

interface CreateResponse extends CommonResponse {
  data: {
    id: number;
    name: string;
    description: string;
    startDate: string;
    endDate: string;
    owner: {
      id: number;
      name: string;
    };
  };
}

interface MemberDataResponse extends CommonResponse {
  data: {
    members: [
      {
        id: number;
        name: string;
        image: string;
      },
    ];
  };
}

interface MemberCreateRequest {
  memberEmail: string;
}

export interface MemberData {
  id: number;
  name: string;
  image: string;
}

interface MemberCreateResponse extends CommonResponse {
  data: MemberData[];
}

interface MemberDeleteResponse extends CommonResponse {
  data: {
    id: number;
  };
}

interface DeleteResponse extends CommonResponse {
  data: {
    id: number;
  };
}

interface ModifyRequest {
  name: string;
  description: string;
  startDate: string;
  endDate: string;
}

interface ModifyResponse extends CommonResponse {
  data: {
    id: number;
    name: string;
    description: string;
    startDate: string;
    endDate: string;
    owner: {
      id: number;
      name: string;
    };
  };
}

export interface ProjectData {
  id: number;
  name: string;
  description: string;
  startDate: string;
  endDate: string;
  owner: {
    id: number;
    name: string;
  };
}

export interface DataResponse extends CommonResponse {
  data: ProjectData;
}
