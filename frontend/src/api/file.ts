import { CommonResponse, http } from './http';

export default {
  file: () => http.get<FileResponse>('files'),
  upload: (data: UploadRequest, taskId: number) =>
    http.post<UploadResponse>(`files?task_id=${taskId}`, data, { headers: { 'Content-Type': 'multipart/form-data' } }),
  delete: () => http.delete<DeleteResponse>('files'),
  download: (fileId: number) => http.download<string>(`files/${fileId}`, { responseType: 'blob' }),
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
  version: number;
  file: File;
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
