import {Fab, makeStyles} from "@material-ui/core";
import AddIcon from '@mui/icons-material/Add';
import {useState} from "react";
import Header from "../../components/Header";
import AppFormAdd from "../../components/AppFormAdd";

const useStyles = makeStyles((theme) => ({
    addApp: {
        bottom: 30,
        right: 30,
        position: "absolute"
    }
}));

function MainContainer() {
    const classes = useStyles();
    const [open, setOpen] = useState(false);

    return (
        <div>
            <Header/>

            <Fab size="small" color="secondary" aria-label="add" className={classes.addApp}>
                <AddIcon onClick={()=> setOpen(true)}/>
            </Fab>
            <AppFormAdd open={open} setOpen={setOpen}/>
        </div>
    );
}

export default MainContainer;
