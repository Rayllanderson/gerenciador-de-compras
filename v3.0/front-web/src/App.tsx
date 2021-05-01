import GlobalStyle from "./styles/global";
import {BrowserRouter} from "react-router-dom";
import {ThemeProvider} from "styled-components";
import React from "react";
import useToggleTheme from "./hooks/useToggleTheme";
import {AuthProvider} from "./contexts/AuthContext";
import {LoginProvider} from "./contexts/LoginContext";
import Routes from "./routes";
import {ToastProvider} from "./contexts/ToastContext";
import {VisibilityCardItemProvider} from "./contexts/CardItemVisibilityContext";
import {CardItemActionProvider} from "./contexts/SelectedItemsContext";
import {RegisterProvider} from "./contexts/RegisterContext";
import {CategoryProvider} from "./contexts/CategoryContext";
import {PaginationProvider} from "./contexts/PaginationContext";
import {ProductProvider} from "./contexts/ProductContext";
import {ModalProvider} from "./contexts/ModalContext";
import {AlertProvider} from "./contexts/AlertContext";
import {GeneralProvider} from "./contexts/GeneralContex";
import {ConfirmModalProvider} from "./contexts/ConfirmModalContext";
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
