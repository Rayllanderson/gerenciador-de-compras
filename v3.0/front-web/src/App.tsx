import GlobalStyle from "./styles/global";
import {BrowserRouter} from "react-router-dom";
import Navbar from "./components/Navbar";
import {ThemeProvider} from "styled-components";
import React from "react";
import useToggleTheme from "./hooks/useToggleTheme";
import ProductPage from "./pages/productPage";
import CategoryPage from "./pages/categoryPage";
import LoginPage from "./pages/SignIn";

function App() {
    const {theme} = useToggleTheme();

    return (
        <ThemeProvider theme={theme}>
            <div className="App">
                <BrowserRouter>
                    <GlobalStyle/>
                    <LoginPage/>
                    {/*<Navbar/>*/}
                    {/*<ProductPage/>*/}
                    {/* <CategoryPage/>*/}
                </BrowserRouter>
            </div>
        </ThemeProvider>
    );
}

export default App;
