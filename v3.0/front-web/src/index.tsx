import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import {ThemeProvider} from "./context/toggleTheme";
import {VisibilityCardItemProvider} from "./context/CardItemVisibilityContext";
import {CardItemActionProvider} from "./context/CardItemActionContext";
import {ProductModalProvider} from "./context/ProductModalContext";
import {ToastProvider} from "./context/ToastContext";

ReactDOM.render(
    <React.StrictMode>
        <ThemeProvider>
            <ToastProvider>
                <VisibilityCardItemProvider>
                    <CardItemActionProvider>
                        <ProductModalProvider>
                            <App/>
                        </ProductModalProvider>
                    </CardItemActionProvider>
                </VisibilityCardItemProvider>
            </ToastProvider>
        </ThemeProvider>
    </React.StrictMode>,
    document.getElementById('root')
);