import {Button, makeStyles} from "@material-ui/core";
import {Box, MenuItem, Modal, Select, TextField} from "@mui/material";
import {useState} from "react";
import {createDonation} from "../api/service/donationService";

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


const DonationAdd = ({open, setOpen, appFormId}) => {
    const classes = useStyles();
    const [amount, setAmount] = useState(0);
    const [currency, setCurrency] = useState(0);

    const submit = async () => {
        await createDonation({amount, currency, appFormId});
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
                        label="Amount"
                        type="number"
                        InputLabelProps={{
                            shrink: true,
                        }}
                        value={amount}
                        onChange={e => setAmount(e.target.value)}
                    />
                </Box>
                <Box display={"flex"} justifyContent={"center"} pt={2}>
                    <Select
                        className={classes.field}
                        value={currency}
                        label="Age"
                        onChange={e => setCurrency(e.target.value)}
                    >
                        <MenuItem value={0}>USD</MenuItem>
                        <MenuItem value={1}>EUR</MenuItem>
                    </Select>
                </Box>
                <Box display={"flex"} justifyContent={"center"} pt={2}>
                    <Button variant={"contained"} onClick={submit}>Submit</Button>
                </Box>
            </div>
        </Modal>
    )
};

export default DonationAdd;