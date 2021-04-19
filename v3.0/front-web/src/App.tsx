import GlobalStyle from "./styles/global";
import Navbar from "./components/Navbar";
import {ThemeProvider} from "styled-components";
import React from "react";
import useToggleTheme from "./hooks/useToggleTheme";
import Products from "./pages/products";

function App() {
    const { theme } = useToggleTheme();

    return (
        <ThemeProvider theme={theme}>
            <div className="App">
                <GlobalStyle/>
                <Navbar/>
                <Products/>
            </div>
        </ThemeProvider>
    );
}

export default App;
