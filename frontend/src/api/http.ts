// import { getToken } from '@utils/getToken';
import Axios from 'axios';

export const ROOT = 'https://api.projpolice.com/';

export const instance = Axios.create({
  baseURL: ROOT,
});

export const http = {
  get: <Response = unknown>(url: string) => instance.get<Response>(url).then((response) => response.data),
  post: <Response = unknown, Request = unknown>(url: string, body?: Request) =>
    instance.post<Response>(url, body).then((response) => response.data),
  put: <Response = unknown, Request = unknown>(url: string, body?: Request) =>
    instance.put<Response>(url, body).then((response) => response.data),
  patch: <Response = unknown, Request = unknown>(url: string, body?: Request) =>
    instance.patch<Response>(url, body).then((response) => response.data),
  delete: <Response = unknown>(url: string) => instance.delete<Response>(url).then((response) => response.data),
};

export interface CommonResponse {
  code: number;
  messages: string;
}
