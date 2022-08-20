import './App.css';
import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import AppFormContainer from "./container/AppFormContainer";
import Header from "./components/Header";
import DonationContainer from "./container/DonationContainer";

function App() {
    return (
        <div>
            <BrowserRouter>
                <Header/>
                <Switch>
                    <Route
                        path={"/app/appForm"}
                        render={() => (
                            <AppFormContainer/>
                        )}
                    />
                    <Route
                        path={"/app/donation"}
                        render={() => (
                            <DonationContainer/>
                        )}
                    />
                    <Redirect from="/" to="/app/appForm"/>
                </Switch>
            </BrowserRouter>
        </div>
    );
}

export default App;
