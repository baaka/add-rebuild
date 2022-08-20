import axiosInstance from "../axios";

export const getDonations = () => {
    return axiosInstance.get('donation');
}

export const getDonationsByAppFormId = (appFormId) => {
    return axiosInstance.get(`donation/${appFormId}`);
}

export const createDonation = (donation) => {
    return axiosInstance.post('donation', donation);
};