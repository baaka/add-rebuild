import {Button, makeStyles, TextareaAutosize} from "@material-ui/core";
import {Box, Modal, TextField} from "@mui/material";
import {useState} from "react";
import {createAppForm, getAppForms} from "../api/service/appFormService";

const useStyles = makeStyles((theme) => ({
    modal: {
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
    },
    paper: {
        width: 500,
        borderRadius: 6,
        backgroundColor: "#FFFFFF",
        padding: 25,
        maxHeight: "70%",
        overflowY: "scroll"
    },
    title: {
        fontSize: 25,
        textAlign: "center",
        fontWeight: "500",
    },
    field: {
        width: 400
    }
}));


const AppFormAdd = ({open, setOpen}) => {
    const classes = useStyles();
    const [title, setTitle]= useState("");
    const [description, setDescription]= useState("");

    const submit = async () => {
        await createAppForm({title, description});
        setOpen(false);
    }


    return (
        <Modal
            open={open}
            onClose={() => setOpen(false)}
            className={classes.modal}
            aria-labelledby="simple-modal-title"
            aria-describedby="simple-modal-description"
        >
            <div className={classes.paper}>
                <div className={classes.title}>Add App</div>
                <Box display={"flex"} justifyContent={"center"} pt={2}>
                    <TextField
                        className={classes.field}
                        label="Title"
                        value={title}
                        onChange={e=>setTitle(e.target.value)}
                    />
                </Box>
                <Box display={"flex"} justifyContent={"center"} pt={2}>
                    <TextField
                        className={classes.field}
                        label="Description"
                        multiline
                        rows={8}
                        value={description}
                        onChange={e=>setDescription(e.target.value)}
                    />
                </Box>
                <Box display={"flex"} justifyContent={"center"} pt={2}>
                    <Button variant={"contained"} onClick={submit} >Submit</Button>
                </Box>
            </div>
        </Modal>
    )
};

export default AppFormAdd;