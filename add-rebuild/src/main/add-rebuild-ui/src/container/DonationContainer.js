import {useEffect, useState} from "react";
import {getDonations} from "../api/service/donationService";
import {makeStyles} from "@material-ui/core";
import Donation from "../components/Donation";

const useStyles = makeStyles((theme) => ({
    addApp: {
        bottom: 30,
        right: 30,
        position: "absolute"
    }
}));

const AppFormContainer = () => {
    const classes = useStyles();
    const [donations, setDonations] = useState([]);
    const [open, setOpen] = useState(false);

    useEffect(() => {
        fetchData();
    }, [])

    const fetchData = async () => {
        let resp = await getDonations();
        setDonations(resp.data);
    }

    return (<div style={{paddingTop: 70}}>
        <Donation/>
    </div>);
}

export default AppFormContainer;