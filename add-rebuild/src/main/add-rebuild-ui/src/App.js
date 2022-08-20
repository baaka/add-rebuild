import './App.css';
import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import AppFormContainer from "./container/AppFormContainer";
import Header from "./components/Header";
import DonationContainer from "./container/DonationContainer";
import {useEffect, useState} from "react";
import Web3 from 'web3'
import {ELECTION_ABI, ELECTION_ADDRESS} from "./api/config";

function App() {
    const [account, setAccount] = useState("");
    const [contract, setContract] = useState(null);

    useEffect(()=> {
        loadBlockchainData();
    }, [])

    const loadBlockchainData =  async () => {
        if (window.ethereum) {
            window.web3 = new Web3(window.ethereum)
            await window.ethereum.enable()
        }
        else if (window.web3) {
            window.web3 = new Web3(window.web3.currentProvider)
        } else {
            console.log("non-eth")
        }
         // = new Web3(Web3.givenProvider || "http://localhost:7545")

        const accounts = await window.web3.eth.getAccounts()
        setAccount(accounts[0])
        const election = new window.web3.eth.Contract(ELECTION_ABI, ELECTION_ADDRESS)
        setContract(election)
    }

    return (
        <div>
            <BrowserRouter>
                <Header/>
                <Switch>
                    <Route
                        path={"/app/appForm"}
                        render={() => (
                            <AppFormContainer contract={contract} account={account}/>
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
