import {useEffect, useState} from "react";
import {getAppForms} from "../service/appFormService";
import AppForm from "../../components/AppForm";
import {Grid} from "@mui/material";

const AppFormContainer = () => {
    const [AppForms, setAppForms] = useState([]);

    useEffect(() => {
        fetchData();
    }, [])

    const fetchData = async () => {
        let resp = await getAppForms();
        setAppForms(resp.data);
    }

    return (<div style={{paddingTop:70}}>
        <Grid container spacing={1}>
            {AppForms.map(e => <Grid item xs={2}>
                <div style={{padding:10}}>
                    <AppForm title={e.title} description={e.description} creationTime={e.creationTime}
                             author={e.author}/>
                </div>
            </Grid>)}
        </Grid>
    </div>);
}

export default AppFormContainer;