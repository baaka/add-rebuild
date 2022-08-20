import {Button, makeStyles} from "@material-ui/core";
import {Box, MenuItem, Modal, Select, TextField} from "@mui/material";
import {useState} from "react";
import {createAppForm} from "../api/service/appFormService";

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


const AppFormAdd = ({open, setOpen, afterSubmit, contract, account}) => {
    const classes = useStyles();
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [type, setType] = useState(0);
    const [amountRequested, setAmountRequested] = useState();
    const [amountRequestedCurrency, setAmountRequestedCurrency] = useState();

    const submit = async () => {
        await contract.methods.addAppForm(title).send({from: account})
        await createAppForm({title, description, amountRequested, amountRequestedCurrency, type});
        afterSubmit();
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
                <div className={classes.title}>Add Application Form</div>
                <Box display={"flex"} justifyContent={"center"} pt={2}>
                    <TextField
                        className={classes.field}
                        label="Title"
                        value={title}
                        onChange={e => setTitle(e.target.value)}
                    />
                </Box>
                <Box display={"flex"} justifyContent={"center"} pt={2}>
                    <Select
                        className={classes.field}
                        value={type}
                        label="Type"
                        onChange={e => setType(e.target.value)}
                    >
                        <MenuItem value={0}>General</MenuItem>
                        <MenuItem value={1}>Rebuild</MenuItem>
                        <MenuItem value={2}>Education</MenuItem>
                        <MenuItem value={3}>Entertainment</MenuItem>
                        <MenuItem value={4}>Help Needed</MenuItem>
                        <MenuItem value={5}>Municipal Project</MenuItem>
                    </Select>
                </Box>
                <Box display={"flex"} justifyContent={"center"} pt={2}>
                    <TextField
                        className={classes.field}
                        label="Description"
                        multiline
                        rows={8}
                        value={description}
                        onChange={e => setDescription(e.target.value)}
                    />
                </Box>
                <Box display={"flex"} justifyContent={"center"} pt={2}>
                    <TextField
                        className={classes.field}
                        label="Requested Amount"
                        type="number"
                        value={amountRequested}
                        onChange={e => setAmountRequested(e.target.value)}
                    />
                </Box>
                <Box display={"flex"} justifyContent={"center"} pt={2}>
                    <Select
                        className={classes.field}
                        value={amountRequestedCurrency}
                        label="Age"
                        onChange={e => setAmountRequestedCurrency(e.target.value)}
                    >
                        <MenuItem value={0}>EUR</MenuItem>
                        <MenuItem value={1}>USD</MenuItem>
                    </Select>
                </Box>
                <Box display={"flex"} justifyContent={"center"} pt={2}>
                    <Button variant={"contained"} onClick={submit}>Submit</Button>
                </Box>
            </div>
        </Modal>
    )
};

export default AppFormAdd;