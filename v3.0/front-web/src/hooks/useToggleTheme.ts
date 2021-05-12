import {useContext} from "react";
import {ThemeContext} from "../contexts/toggleTheme";

function useToggleTheme() {
    return useContext(ThemeContext);
}

export default useToggleTheme;