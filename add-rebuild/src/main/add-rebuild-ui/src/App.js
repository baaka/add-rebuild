import './App.css';
import MainContainer from "./api/container/MainContainer";
import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import AppFormContainer from "./api/container/AppFormContainer";
import Header from "./components/Header";

function App() {
    return (
        <div>
            <Header/>
            <BrowserRouter>
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
                            <MainContainer/>
                        )}
                    />
                    <Redirect from="/" to="/app/appForm"/>
                </Switch>
            </BrowserRouter>
        </div>
    );
}

export default App;

