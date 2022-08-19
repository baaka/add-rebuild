import axios from "axios";
import qs from "qs";
import { BASE_REST_URL } from "../appUtil";

const instance = axios.create({
    baseURL: `${BASE_REST_URL}`,
    timeout: 30000,
    paramsSerializer: function (params) {
        return qs.stringify(params, { arrayFormat: "repeat" });
    },
});

export default instance;
