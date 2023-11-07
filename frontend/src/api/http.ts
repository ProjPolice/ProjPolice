import Axios from 'axios';

export const ROOT = 'https://api.projpolice.com/';

const httpAxios = Axios.create({
  baseURL: ROOT,
});

// httpAxios.interceptors.request.use((config) => {
//   // Request URL 보는법
//   // console.log(config.url);
//   const newConfig = { ...config };
//   const token = store.getState().user.token;
//   if (token) {
//     newConfig.headers.Authorization = `Bearer ${token}`;
//   }
//   return newConfig;
// });

export const http = {
  get: <Response = unknown>(url: string) => httpAxios.get<Response>(url).then((response) => response.data),
  post: <Response = unknown, Request = unknown>(url: string, body?: Request) =>
    httpAxios.post<Response>(url, body).then((response) => response.data),
  put: <Response = unknown, Request = unknown>(url: string, body?: Request) =>
    httpAxios.put<Response>(url, body).then((response) => response.data),
  patch: <Response = unknown, Request = unknown>(url: string, body?: Request) =>
    httpAxios.patch<Response>(url, body).then((response) => response.data),
  delete: <Response = unknown>(url: string) => httpAxios.delete<Response>(url).then((response) => response.data),
};

export interface CommonResponse {
  statusCode: number;
  messages: string;
}
