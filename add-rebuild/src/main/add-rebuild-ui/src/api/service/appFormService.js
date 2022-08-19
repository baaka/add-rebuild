import axiosInstance from "../axios";

export const getAppForms = () => {
    return axiosInstance.get('appForm');
}

export const createAppForm = (appForm) => {
    return axiosInstance.post('appForm', appForm);
}

export const updateAppForm = (appForm) => {
    return axiosInstance.put('appForm', appForm);
};

export const deleteAppForm = (appFormId) => {
    return axiosInstance.delete(`appForm/${appFormId}`);
}