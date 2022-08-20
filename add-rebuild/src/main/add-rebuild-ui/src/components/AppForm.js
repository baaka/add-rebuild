import * as React from 'react';
import {useEffect, useState} from 'react';
import {styled} from '@mui/material/styles';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardMedia from '@mui/material/CardMedia';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Collapse from '@mui/material/Collapse';
import Avatar from '@mui/material/Avatar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import {red} from '@mui/material/colors';
import HowToVoteIcon from '@mui/icons-material/HowToVote';
import VolunteerActivismIcon from '@mui/icons-material/VolunteerActivism';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import {format} from "date-fns";
import {getDefaultDateTimeFormat} from "../appUtil";
import DonationAdd from "./DonationAdd";
import {getDonationsByAppFormId} from "../api/service/donationService";
import InfoCard from "./InfoCard";

const ExpandMore = styled((props) => {
    const {expand, ...other} = props;
    return <IconButton {...other} />;
})(({theme, expand}) => ({
    transform: !expand ? 'rotate(0deg)' : 'rotate(180deg)',
    marginLeft: 'auto',
    transition: theme.transitions.create('transform', {
        duration: theme.transitions.duration.shortest,
    }),
}));

const AppForm = ({id, title, description, creationTime, author, type, amountRequested, amountRequestedCurrency}) => {
    const [expanded, setExpanded] = React.useState(false);
    const [openAddDonation, setOpenAddDonation] = useState(false);
    const [donations, setDonations] = useState(null);
    const [totalDonatedUSD, setTotalDonatedUSD] = useState(0);
    const [totalDonatedEUR, setTotalDonatedEUR] = useState(0);

    useEffect(() => {
        fetchData();
    }, [])

    const fetchData = async () => {
        let resp = await getDonationsByAppFormId(id);
        setDonations(resp.data.length > 0 ? resp.data : null);
        calcTotalDonations(resp.data);
    }

    const afterSubmit = () => {
        fetchData();
    };

    const calcTotalDonations = (data) => {
        let totalUSD = 0;
        let totalEUR = 0;
        if (data && data.length > 0) {
            for (let d in data) {
                if (data[d].currency === 'USD') {
                    totalUSD += data[d].amount;
                } else {
                    totalEUR += data[d].amount;
                }
            }
        }

        setTotalDonatedUSD(totalUSD);
        setTotalDonatedEUR(totalEUR);
    };

    const handleExpandClick = () => {
        setExpanded(!expanded);
    };

    const getType = () => {
        let result = null;
        if (type) {
            switch (type) {
                case 'GENERAL':
                    result = 'General';
                    break;
                case 'REBUILD':
                    result = 'Rebuild';
                    break;
                case 'EDUCATION':
                    result = 'Education';
                    break;
                case 'ENTERTAINMENT':
                    result = 'Entertainment';
                    break;
                case 'HELP_NEEDED':
                    result = 'Help Needed';
                    break;
                case 'MUNICIPAL':
                    result = 'Municipal Project';
                    break;
            }
        }
        return result;
    };

    return (
        <Card sx={{width: 400}}>
            <DonationAdd open={openAddDonation} setOpen={setOpenAddDonation} appFormId={id} afterSubmit={afterSubmit}/>
            <CardHeader
                avatar={
                    <Avatar sx={{bgcolor: red[500]}} aria-label="recipe">
                        {author.username.toUpperCase().charAt(0)}
                    </Avatar>
                }
                title={title}
                subheader={format(Date.parse(creationTime), getDefaultDateTimeFormat())}
            />
            <Typography style={{marginLeft: 20}} variant="body2" color="text.secondary">
                <div>
                    <b>Author: {author.username}</b>
                    <br/>
                    {type && <b>Type: {getType()}</b>}
                    <br/>
                    {amountRequested && <b>Amount Requested: {amountRequested} {amountRequestedCurrency}</b>}
                    <br/>
                    {<b>Amount Donated: {totalDonatedUSD} USD, {totalDonatedEUR} EUR</b>}
                </div>
            </Typography>
            <CardMedia
                component="img"
                image="https://img.freepik.com/free-vector/businessman-get-idea_1133-350.jpg"
                alt="Paella dish"
            />
            <CardContent style={{height: 100}}>
                <Typography variant="body2" color="text.secondary">
                    {description}
                </Typography>
            </CardContent>
            <CardActions disableSpacing>
                <IconButton aria-label="add to favorites">
                    <HowToVoteIcon/>
                </IconButton>
                <IconButton aria-label="share">
                    <VolunteerActivismIcon onClick={() => setOpenAddDonation(true)}/>
                </IconButton>
                {donations && <ExpandMore
                    expand={expanded}
                    onClick={handleExpandClick}
                    aria-expanded={expanded}
                    aria-label="show more"
                >
                    <ExpandMoreIcon/>
                </ExpandMore>}
            </CardActions>
            {donations && <Collapse in={expanded} timeout="auto" unmountOnExit>
                <CardContent>
                    {donations.map(e => <Typography>
                        <InfoCard data={e}/>
                    </Typography>)}
                </CardContent>
            </Collapse>}
        </Card>
    );
}

export default AppForm;
