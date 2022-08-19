import {Button, makeStyles} from "@material-ui/core";
import {Modal} from "@mui/material";

const useStyles = makeStyles((theme) => ({
    modal: {
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
    },
    paper: {
        width: "1000px",
        borderRadius: 6,
        backgroundColor: "#FFFFFF",
        padding: "25px",
        maxHeight: "70%",
        overflowY: "scroll"
    },
    title: {
        textAlign: "center",
        fontWeight: "500",
    },
}));


const AppFormAdd = ({open, setOpen}) => {
    const classes = useStyles();

    return (
        <Modal
            open={open}
            onClose={()=> setOpen(false)}
            className={classes.modal}
            aria-labelledby="simple-modal-title"
            aria-describedby="simple-modal-description"
        >
            <div className={classes.paper}>
                <div className={classes.title}>Add App</div>
                <Button>Submit</Button>
            </div>
        </Modal>
    )
};

export default AppFormAdd;