import {useEffect, useState} from "react";
import {getDonations} from "../api/service/donationService";
import Donation from "../components/Donation";
import DonationByYearAreaChart from "../components/DonationByYearAreaChart";

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
        {donations.length > 0 && <div>
            <DonationByYearAreaChart donations={donations}/>
            <Donation donations={donations}/>
        </div>}
    </div>);
}

export default AppFormContainer;