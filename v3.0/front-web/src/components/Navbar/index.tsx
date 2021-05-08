import React, {useContext, useEffect, useState} from "react";
import {Container} from './styles';
import ToggleSwitchTheme from "../Switch";
import useToggleTheme from "../../hooks/useToggleTheme";
import {FiShoppingCart} from "react-icons/all";
import {AuthContext} from "../../contexts/AuthContext";
import {DropdownNavbar} from "../Dropdown";
import ImageController from "../../controllers/imageController";


const Navbar: React.FC = () => {
    const {toggleTheme} = useToggleTheme();
    return (
        <Container className="navbar ">
            <div className="container" style={{maxWidth: 750}}>
                <div className=" d-flex justify-content-start">
                    <FiShoppingCart size={22}/>
                </div>
                <div className="d-flex justify-content-end">
                    <ToggleSwitchTheme toggleTheme={toggleTheme}/>
                    <DropdownNavbar/>
                </div>
            </div>
        </Container>

    );
}

export default Navbar;