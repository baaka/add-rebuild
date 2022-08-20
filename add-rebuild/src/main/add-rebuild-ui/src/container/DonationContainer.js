import {useEffect, useState} from "react";
import {getDonations} from "../api/service/donationService";
import Donation from "../components/Donation";

const AppFormContainer = () => {
    const [donations, setDonations] = useState([]);

    useEffect(() => {
        fetchData();
    }, [])

    const fetchData = async () => {
        let resp = await getDonations();
        setDonations(resp.data);
    }

    return (<div style={{paddingTop: 70}}>
        <Donation donations={donations}/>
    </div>);
}

export default AppFormContainer;