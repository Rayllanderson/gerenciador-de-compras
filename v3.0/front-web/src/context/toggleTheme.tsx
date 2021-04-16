import React, { createContext } from "react";
import { DefaultTheme } from "styled-components";


import light from "../styles/themes/light";
import dark from "../styles/themes/dark";
import usePersistedState from "../utils/usePersistedState";

interface ThemeContextData {
    toggleTheme(): void;
    theme: DefaultTheme;
}

const ThemeContext = createContext<ThemeContextData>({} as ThemeContextData);

const ThemeContextProvider: React.FC = ({ children }) => {
    const [theme, setTheme] = usePersistedState<DefaultTheme>('theme', dark);

    function toggleTheme() {
        setTheme(theme.title === 'light' ? dark : light);
    }

    return (
        <ThemeContext.Provider value={{ toggleTheme, theme }}>
            {children}
        </ThemeContext.Provider>
    );
};

export { ThemeContext, ThemeContextProvider };