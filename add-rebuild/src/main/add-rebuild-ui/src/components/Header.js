import {AppBar, Button, makeStyles, Toolbar, Typography} from "@material-ui/core";
import {login} from "../api/service/userService";

const useStyles = makeStyles((theme) => ({
    appBar: {
        zIndex: theme.zIndex.drawer + 1,
    },
    root: {
        flexGrow: 1,
    },
    title: {
        flexGrow: 1,
    },
}));

const Header = () => {
    const classes = useStyles();

    return (
        <div className={classes.root}>
            <AppBar position="fixed" className={classes.appBar}>
                <Toolbar>
                    <Typography variant="h6" className={classes.title}>
                        App Rebuild
                    </Typography>
                    <div>
                        <Button color="inherit" onClick={()=>{}}>Logout</Button>
                    </div>
                </Toolbar>
                <Button onClick={()=> {
                    login({
                        username:"admin",
                        password:"admin",
                    })
                }}>Login</Button>
            </AppBar>
        </div>
    )
};

export default Header;