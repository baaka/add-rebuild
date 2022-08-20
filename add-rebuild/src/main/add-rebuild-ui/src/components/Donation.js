import * as React from 'react';
import FastfoodIcon from '@mui/icons-material/Fastfood';
import LaptopMacIcon from '@mui/icons-material/LaptopMac';
import HotelIcon from '@mui/icons-material/Hotel';
import RepeatIcon from '@mui/icons-material/Repeat';
import Typography from '@mui/material/Typography';
import {
    Timeline,
    TimelineConnector,
    TimelineContent,
    TimelineDot, TimelineItem,
    TimelineOppositeContent,
    TimelineSeparator
} from "@material-ui/lab";
import AttachMoneyIcon from '@mui/icons-material/AttachMoney';
import {Grid} from "@mui/material";
import AppForm from "./AppForm";
import {format} from "date-fns";
import {getDefaultDateTimeFormat} from "../appUtil";
import EuroIcon from '@mui/icons-material/Euro';

const Donation = ({donations = []}) => {
    return (
        <Timeline>
            {donations.map(e =>
                <TimelineItem>
                    <TimelineOppositeContent
                        sx={{m: 'auto 0'}}
                        align="right"
                        variant="body2"
                        color="text.secondary"
                    >
                        {format(Date.parse(e.creationTime), getDefaultDateTimeFormat())}
                    </TimelineOppositeContent>
                    <TimelineSeparator>
                        <TimelineConnector/>
                        <TimelineDot color={e.currency === 'USD' ? "primary" : "secondary"}>
                            {e.currency === 'USD' ? <AttachMoneyIcon/> : <EuroIcon/>}
                        </TimelineDot>
                        <TimelineConnector/>
                    </TimelineSeparator>
                    <TimelineContent sx={{py: '12px', px: 2}}>
                        <Typography variant="h6" component="span">
                            {e.amount} {e.currency}
                        </Typography>
                        <Typography>{e.author.username}</Typography>
                    </TimelineContent>
                </TimelineItem>
            )}
        </Timeline>
    );
}

export default Donation;