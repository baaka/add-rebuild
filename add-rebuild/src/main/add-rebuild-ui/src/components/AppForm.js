import {Box} from "@material-ui/core";
import {useEffect, useState} from "react";
import {getAppForms} from "../api/service/appFormService";

const AppForm = () => {
    const [AppForms, setAppForms] = useState([]);

    useEffect(() => {
        fetchData();
    },[])


    const fetchData = async () => {
        let resp = await getAppForms();
        setAppForms(resp.data);
    }

    return (
        <Box>
            {AppForms.map(e=> <Box>
                <Box>{e.title}</Box>
                <Box>{e.description}</Box>
                <Box>{e.creationTime}</Box>
            </Box>)}
        </Box>
    )
};

export default AppForm;