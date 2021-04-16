import React from "react";
import {Container} from './style';
import ToggleSwitchTheme from "../Switch/Index";
import useToggleTheme from "../../hooks/useToggleTheme";
import {Dropdown} from "react-bootstrap";
import {FiSettings, FiShoppingCart} from "react-icons/all";


const Header: React.FC = () => {
    const {toggleTheme} = useToggleTheme();
    return (
        <Container className="navbar ">
            <div className="container" style={{maxWidth: 750}}>
                <div className=" d-flex justify-content-start">
                    <FiShoppingCart size={22}/>
                </div>
                <div className="d-flex justify-content-end">
                    <ToggleSwitchTheme toggleTheme={toggleTheme}/>
                    <Dropdown className="content-items" drop='down'>
                        <Dropdown.Toggle variant="dropdown">
                            < FiSettings size={21}/>
                        </Dropdown.Toggle>
                    </Dropdown>
                </div>
            </div>
        </Container>
    );
}

export default Header;