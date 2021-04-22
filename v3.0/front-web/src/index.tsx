import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import {ThemeContextProvider} from "./context/toggleTheme";
import {VisibilityCardItemContextProvider} from "./context/CardItemVisibilityContext";
import {CardItemActionContextProvider} from "./context/CardItemActionContext";
import {ProductModalContextProvider} from "./context/ProductModalContext";

ReactDOM.render(
    <React.StrictMode>
        <ThemeContextProvider>
            <VisibilityCardItemContextProvider>
                <CardItemActionContextProvider>
                    <ProductModalContextProvider>
                        <App/>
                    </ProductModalContextProvider>
                </CardItemActionContextProvider>
            </VisibilityCardItemContextProvider>
        </ThemeContextProvider>
    </React.StrictMode>,
    document.getElementById('root')
);