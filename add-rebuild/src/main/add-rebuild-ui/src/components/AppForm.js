import * as React from 'react';
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

const AppForm = ({title, description, creationTime, author, donations}) => {
    const [expanded, setExpanded] = React.useState(false);

    const handleExpandClick = () => {
        setExpanded(!expanded);
    };

    return (
        <Card sx={{maxWidth: 345}}>
            <CardHeader
                avatar={
                    <Avatar sx={{bgcolor: red[500]}} aria-label="recipe">
                        R
                    </Avatar>
                }
                title={title}
                subheader={format(Date.parse(creationTime), getDefaultDateTimeFormat())}
            />
            <CardMedia
                component="img"
                image="https://img.freepik.com/free-vector/businessman-get-idea_1133-350.jpg"
                alt="Paella dish"
            />
            <CardContent>
                <Typography variant="body2" color="text.secondary">
                    {description}
                </Typography>
            </CardContent>
            <CardActions disableSpacing>
                <IconButton aria-label="add to favorites">
                    <HowToVoteIcon/>
                </IconButton>
                <IconButton aria-label="share">
                    <VolunteerActivismIcon/>
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
                        <div>
                            <ol>
                                <li>Author: {e.author}</li>
                                <li>Amount:{e.amount} {e.currency}</li>
                            </ol>
                        </div>
                    </Typography>)}
                </CardContent>
            </Collapse>}
        </Card>
    );
}

export default AppForm;
