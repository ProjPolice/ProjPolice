import { CommonResponse, http } from './http';

export default {
  file: () => http.get<FileResponse>('files'),
  upload: (data: UploadRequest) => http.post<UploadResponse>('files', data),
  delete: () => http.delete<DeleteResponse>('files'),
};

interface FileResponse extends CommonResponse {
  data: [
    {
      id: number;
      name: string;
      comment: string;
      uuid: string;
      version: number;
      extension: string;
      taskId: number;
    },
  ];
}

interface UploadRequest {
  comment: string;
  version: number;
  file: string;
}

interface UploadResponse extends CommonResponse {
  data: {
    id: number;
    name: string;
    comment: string;
    uuid: string;
    version: number;
    extension: string;
    taskId: number;
  };
}

interface DeleteResponse extends CommonResponse {
  data: {
    id: number;
    name: string;
  };
}
