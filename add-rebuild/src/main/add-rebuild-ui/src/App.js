import './App.css';
import MainContainer from "./api/container/MainContainer";
import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";

function App() {
    return (
        <BrowserRouter>
            <Switch>
                <Route
                    path={"/app/appForm"}
                    render={() => (
                        <MainContainer/>
                    )}
                />
                <Route
                    path={"/app/donation"}
                    render={() => (
                        <MainContainer/>
                    )}
                />
                <Redirect from="/" to="/app/appForm"/>
            </Switch>
        </BrowserRouter>
    );
}

export default App;
