import GlobalStyle from "./styles/global";
import {BrowserRouter} from "react-router-dom";
import {ThemeProvider} from "styled-components";
import React from "react";
import useToggleTheme from "./hooks/useToggleTheme";
import {AuthProvider} from "./context/AuthContext";
import {LoginContextProvider} from "./context/LoginContext";
import Routes from "./routes";

function App() {
    const {theme} = useToggleTheme();

    return (
        <ThemeProvider theme={theme}>
            <div className="App">
                <BrowserRouter>
                    <AuthProvider>
                        <LoginContextProvider>
                            <GlobalStyle/>
                            <Routes />
                        </LoginContextProvider>
                    </AuthProvider>
                </BrowserRouter>
            </div>
        </ThemeProvider>
    );
}

export default App;
