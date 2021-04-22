import GlobalStyle from "./styles/global";
import Navbar from "./components/Navbar";
import {ThemeProvider} from "styled-components";
import React from "react";
import useToggleTheme from "./hooks/useToggleTheme";
import ProductPage from "./pages/productPage";
import CategoryPage from "./pages/categoryPage";

function App() {
    const { theme } = useToggleTheme();

    return (
        <ThemeProvider theme={theme}>
            <div className="App">
                <GlobalStyle/>
                <Navbar/>
                {/*<ProductPage/>*/}
                 <CategoryPage/>
            </div>
        </ThemeProvider>
    );
}

export default App;
