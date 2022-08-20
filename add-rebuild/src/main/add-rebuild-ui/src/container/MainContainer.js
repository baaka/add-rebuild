import {Fab, makeStyles} from "@material-ui/core";
import AddIcon from '@mui/icons-material/Add';
import {useState} from "react";
import AppFormAdd from "../components/AppFormAdd";
import Header from "../components/Header";

const useStyles = makeStyles((theme) => ({
    addApp: {
        bottom: 30,
        right: 30,
        position: "absolute"
    }
}));

const  MainContainer = () => {
    const classes = useStyles();
    const [open, setOpen] = useState(false);

    return (
        <div>
            <Header/>

            <Fab size="small" color="secondary" aria-label="add" className={classes.addApp}>
                <AddIcon onClick={() => setOpen(true)}/>
            </Fab>
            {/*<AppFormAdd open={open} setOpen={setOpen}/>*/}
        </div>
    );
    const MainContainer = () => {
        return <div>Hello</div>
    }
}

export default MainContainer;