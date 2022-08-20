import * as React from 'react';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import {format} from "date-fns";
import {getDefaultDateTimeFormat} from "../appUtil";

const bull = (
    <Box
        component="span"
        sx={{display: 'inline-block', mx: '2px', transform: 'scale(0.8)'}}
    >
        •
    </Box>
);

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
