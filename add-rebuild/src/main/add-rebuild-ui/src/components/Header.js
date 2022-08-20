import {AppBar, Button, makeStyles, Toolbar, Typography} from "@material-ui/core";
import {useState} from "react";
import {withRouter} from "react-router-dom";

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
    menuContainer: {
        flexGrow: 1,
    },
    menuItemSelected: {
        color: "red"
    },
    menuItem: {
        color: "white"
    }
}));

const Header = ({history}) => {
    const classes = useStyles();
    const [selectedMenuItem, setSelectedMenuItem] = useState('appForm');

    const onLogoutClick = () => {
        window.location.href = window.location.origin + "/logout";
    };

    const redirectRo = (routeName) => {
        setSelectedMenuItem(routeName);
        history.push(routeName);
    };

    return (
        <div className={classes.root}>
            <AppBar position="fixed" className={classes.appBar}>
                <Toolbar>
                    <Typography variant="h6" className={classes.title}>
                        App Rebuild
                    </Typography>
                    <div className={classes.menuContainer}>
                        <Button
                            onClick={() => redirectRo('appForm')}
                            className={selectedMenuItem === 'appForm' ? classes.menuItemSelected : classes.menuItem}>App
                            Forms</Button>
                        <Button
                            onClick={() => redirectRo('donation')}
                            className={selectedMenuItem === 'donation' ? classes.menuItemSelected : classes.menuItem}>Donations</Button>
                        <Button
                            className={selectedMenuItem === 'forum' ? classes.menuItemSelected : classes.menuItem}>Forum</Button>
                        <Button
                            className={selectedMenuItem === 'analytics' ? classes.menuItemSelected : classes.menuItem}>Analytics</Button>
                    </div>
                    <div>
                        <Button color="inherit" onClick={onLogoutClick}>Logout</Button>
                    </div>
                </Toolbar>
            </AppBar>
        </div>
    )
};

export default withRouter(Header);