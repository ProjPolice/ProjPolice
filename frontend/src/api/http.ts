import { getAccessToken } from '@utils/getToken';
import Axios, { AxiosRequestConfig } from 'axios';

export const ROOT = 'https://api.projpolice.com/';

export const instance = Axios.create({
  baseURL: ROOT,
});

instance.interceptors.request.use((config) => {
  const newConfig = { ...config };
  const accessToken = getAccessToken();
  if (accessToken) {
    newConfig.headers.Authorization = `Bearer ${accessToken}`;
  }
  return newConfig;
});

instance.interceptors.response.use(
  function (response) {
    return response;
  },
  function (error) {
    console.log(error);
  },
);

export const http = {
  get: <Response = unknown>(url: string) => instance.get<Response>(url).then((response) => response.data),
  post: <Response = unknown, Request = unknown>(url: string, body?: Request, config?: AxiosRequestConfig) =>
    instance.post<Response>(url, body, config).then((response) => response.data),
  put: <Response = unknown, Request = unknown>(url: string, body?: Request) =>
    instance.put<Response>(url, body).then((response) => response.data),
  patch: <Response = unknown, Request = unknown>(url: string, body?: Request, config?: AxiosRequestConfig) =>
    instance.patch<Response>(url, body, config).then((response) => response.data),
  delete: <Response = unknown>(url: string) => instance.delete<Response>(url).then((response) => response.data),
  download: <Response = unknown>(url: string, config?: AxiosRequestConfig) =>
    instance.get<Response>(url, config).then((response) => response),
};

export interface CommonResponse {
  code: number;
  messages: string;
}
