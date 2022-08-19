import axiosInstance from "../axios";


export const login = (form) => {
    return axiosInstance.post(`/users/login`, form);
}
