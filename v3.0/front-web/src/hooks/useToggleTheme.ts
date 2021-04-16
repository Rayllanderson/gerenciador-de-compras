import {useContext} from "react";
import {ThemeContext} from "../context/toggleTheme";

function useToggleTheme() {
    return useContext(ThemeContext);
}

export default useToggleTheme;