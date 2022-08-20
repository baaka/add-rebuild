import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import {format} from "date-fns";
import {getDefaultDateTimeFormat} from "../appUtil";

const InfoCard = ({data}) => {
    return (
        <Card sx={{minWidth: 275}} style={{marginTop: 10}}>
            <CardContent>
                <Typography sx={{fontSize: 14}} color="text.secondary" gutterBottom>
                    {format(Date.parse(data.creationTime), getDefaultDateTimeFormat())}
                </Typography>
                <Typography variant="h5" component="div">
                    Author: {data.author.username}
                </Typography>
                <Typography sx={{mb: 1.5}} color="text.secondary">
                    {data.amount} {data.currency}
                </Typography>
            </CardContent>
        </Card>
    );
}

export default InfoCard;
