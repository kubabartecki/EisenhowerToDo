import axios, { AxiosRequestConfig, AxiosResponse } from 'axios';

const apiClient = axios.create({
  baseURL: process.env.REACT_APP_API_BASE_URL,
});

apiClient.interceptors.response.use(
  (response: AxiosResponse) => response,
  (error) => {
    // Handle errors
    console.error('API Error:', error);
    return Promise.reject(error);
  }
);

export const get = <T>(url: string, config?: AxiosRequestConfig): Promise<T> => {
  return apiClient.get(url, config).then(response => response.data);
};

export const post = <T>(url: string, data?: any, config?: AxiosRequestConfig): Promise<T> => {
  return apiClient.post(url, data, config).then(response => response.data);
};

export const put = <T>(url: string, data?: any, config?: AxiosRequestConfig): Promise<T> => {
  return apiClient.put(url, data, config).then(response => response.data);
};

export const del = <T>(url: string, config?: AxiosRequestConfig): Promise<T> => {
  return apiClient.delete(url, config).then(response => response.data);
};
