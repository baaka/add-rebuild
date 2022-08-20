import {useEffect, useState} from "react";
import {getAppForms} from "../api/service/appFormService";
import AppForm from "../components/AppForm";
import {Grid} from "@mui/material";
import {Fab, makeStyles} from "@material-ui/core";
import AddIcon from "@mui/icons-material/Add";
import AppFormAdd from "../components/AppFormAdd";

const useStyles = makeStyles((theme) => ({
    addApp: {
        bottom: 30,
        right: 30,
        position: "absolute"
    }
}));

const AppFormContainer = () => {
    const classes = useStyles();
    const [AppForms, setAppForms] = useState([]);
    const [open, setOpen] = useState(false);

    useEffect(() => {
        fetchData();
    }, [])

    const fetchData = async () => {
        let resp = await getAppForms();
        setAppForms(resp.data);
    }

    return (<div style={{paddingTop: 70}}>
        <Grid container spacing={2}>
            {AppForms.map(e => <Grid item xs={3}>
                <div style={{padding: 10}}>
                    <AppForm id={e.id} title={e.title} description={e.description} creationTime={e.creationTime}
                             author={e.author}/>
                </div>
            </Grid>)}
        </Grid>
        <Fab size="small" color="secondary" aria-label="add" className={classes.addApp}>
            <AddIcon onClick={() => setOpen(true)}/>
        </Fab>
        <AppFormAdd open={open} setOpen={setOpen}/>
    </div>);
}

export default AppFormContainer;