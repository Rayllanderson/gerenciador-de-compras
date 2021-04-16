import Switch from "react-switch";
import {FaMoon, FaSun} from "react-icons/all";
import {useContext} from "react";
import {ThemeContext} from "styled-components";

interface Props{
    toggleTheme(): void;
}
export default function ToggleSwitchTheme({toggleTheme}: Props){
    const {title} = useContext(ThemeContext);
    return (
        <Switch onChange={toggleTheme}
                checked={title === 'dark'}
                checkedIcon={false}
                uncheckedIcon={false}
                uncheckedHandleIcon={
                    <div
                        style={{
                            display: "flex",
                            justifyContent: "center",
                            alignItems: "center",
                            height: "100%",
                            fontSize: 15,
                            color: 'white'
                        }}>
                        <FaSun/>
                    </div>
                }
                checkedHandleIcon={
                    <div
                        style={{
                            display: "flex",
                            justifyContent: "center",
                            alignItems: "center",
                            height: "100%",
                            fontSize: 15,
                            color: 'black'
                        }}>
                        <FaMoon/>
                    </div>
                }
                height={10}
                width={32}
                handleDiameter={22}
                offColor={"#414558"}
                onColor={"#414558"}
                onHandleColor="#fff"
                offHandleColor="#000"
                activeBoxShadow='0px 0px 1px 2px #9580ff'
        />
    );
}