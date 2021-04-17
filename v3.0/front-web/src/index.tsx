import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import {ThemeContextProvider} from "./context/toggleTheme";
import {CardActionsContextProvider} from "./context/cardActions";

ReactDOM.render(
    <React.StrictMode>
        <ThemeContextProvider>
            <CardActionsContextProvider>
                <App/>
            </CardActionsContextProvider>
        </ThemeContextProvider>
    </React.StrictMode>,
    document.getElementById('root')
);