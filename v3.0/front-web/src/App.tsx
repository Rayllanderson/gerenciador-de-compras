import GlobalStyle from "./styles/global";
import {BrowserRouter} from "react-router-dom";
import {ThemeProvider} from "styled-components";
import React from "react";
import useToggleTheme from "./hooks/useToggleTheme";
import {AuthProvider} from "./context/AuthContext";
import {LoginProvider} from "./context/LoginContext";
import Routes from "./routes";
import {ToastProvider} from "./context/ToastContext";
import {VisibilityCardItemProvider} from "./context/CardItemVisibilityContext";
import {CardItemActionProvider} from "./context/SelectedItemsContext";
import {ProductModalProvider} from "./context/ProductModalContext";
import {RegisterProvider} from "./context/RegisterContext";
import {CategoryProvider} from "./context/CategoryContext";
import {PaginationProvider} from "./context/PaginationContext";
import {ProductProvider} from "./context/ProductContext";

function App() {
    const {theme} = useToggleTheme();

    return (
        <ThemeProvider theme={theme}>
            <div className="App">
                <BrowserRouter>
                    <ToastProvider>
                        <VisibilityCardItemProvider>
                            <CardItemActionProvider>
                                <ProductModalProvider>
                                    <AuthProvider>
                                        <LoginProvider>
                                            <RegisterProvider>
                                                <PaginationProvider>
                                                    <CategoryProvider>
                                                        <ProductProvider>
                                                            <GlobalStyle/>
                                                            <Routes/>
                                                        </ProductProvider>
                                                    </CategoryProvider>
                                                </PaginationProvider>
                                            </RegisterProvider>
                                        </LoginProvider>
                                    </AuthProvider>
                                </ProductModalProvider>
                            </CardItemActionProvider>
                        </VisibilityCardItemProvider>
                    </ToastProvider>
                </BrowserRouter>
            </div>
        </ThemeProvider>
    );
}

export default App;
