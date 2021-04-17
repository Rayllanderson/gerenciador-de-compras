import GlobalStyle from "./styles/global";
import Header from "./components/Header";
import {ThemeProvider} from "styled-components";
import React from "react";
import useToggleTheme from "./hooks/useToggleTheme";
import Categories from "./pages/categories";

function App() {
    const { theme } = useToggleTheme();

    return (
        <ThemeProvider theme={theme}>
            <div className="App">
                <GlobalStyle/>
                <Header/>
                <Categories/>
            </div>
        </ThemeProvider>
    );
}

export default App;
