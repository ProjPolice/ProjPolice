import { CommonResponse, http } from './http';

export default {
  data: ({ taskId }: FileDataRequest) => http.get<FileDataResponse>(`files?task_id=${taskId}`),
  upload: (data: UploadRequest, taskId: number) =>
    http.post<UploadResponse>(`files?task_id=${taskId}`, data, { headers: { 'Content-Type': 'multipart/form-data' } }),
  delete: () => http.delete<DeleteResponse>('files'),
  download: (fileId: number) => http.download<Blob>(`files/${fileId}`, { responseType: 'blob' }),
};

interface FileDataRequest {
  projectId?: number;
  taskId?: number;
}

export interface FileData {
  id: number;
  name: string;
  comment: string;
  uuid: string;
  version: number;
  extension: string;
  taskId: number;
  createdAt: string;
}

interface FileDataResponse extends CommonResponse {
  data: FileData[];
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
