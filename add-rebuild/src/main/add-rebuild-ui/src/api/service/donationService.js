import axiosInstance from "../axios";

export const getDonationsByAppFormId = (appFormId) => {
    return axiosInstance.get(`donation/${appFormId}`);
}

export const createDonation = (donation) => {
    return axiosInstance.post('donation', donation);
};