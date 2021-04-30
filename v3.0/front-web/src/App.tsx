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
import {RegisterProvider} from "./context/RegisterContext";
import {CategoryProvider} from "./context/CategoryContext";
import {PaginationProvider} from "./context/PaginationContext";
import {ProductProvider} from "./context/ProductContext";
import {ModalProvider} from "./context/ModalContext";
import {AlertProvider} from "./context/AlertContext";
import {GeneralProvider} from "./context/GeneralContex";
import {ConfirmModalProvider} from "./context/ConfirmModalContext";
import {ConfirmModal} from "./components/Modal/ConfirmModal";

function App() {
    const {theme} = useToggleTheme();

    return (
        <ThemeProvider theme={theme}>
            <div className="App">
                <BrowserRouter>
                    <ToastProvider>
                        <VisibilityCardItemProvider>
                            <CardItemActionProvider>
                                <AuthProvider>
                                    <LoginProvider>
                                        <RegisterProvider>
                                            <PaginationProvider>
                                                <ModalProvider>
                                                    <AlertProvider>
                                                        <GeneralProvider>
                                                            <CategoryProvider>
                                                                <ProductProvider>
                                                                    <ConfirmModalProvider>
                                                                        <GlobalStyle/>
                                                                        <Routes/>
                                                                        <ConfirmModal/>
                                                                    </ConfirmModalProvider>
                                                                </ProductProvider>
                                                            </CategoryProvider>
                                                        </GeneralProvider>
                                                    </AlertProvider>
                                                </ModalProvider>
                                            </PaginationProvider>
                                        </RegisterProvider>
                                    </LoginProvider>
                                </AuthProvider>
                            </CardItemActionProvider>
                        </VisibilityCardItemProvider>
                    </ToastProvider>
                </BrowserRouter>
            </div>
        </ThemeProvider>
    );
}

export default App;
