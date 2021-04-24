import React, {useContext} from "react";
import {Container} from './styles';
import ToggleSwitchTheme from "../Switch";
import useToggleTheme from "../../hooks/useToggleTheme";
import {Dropdown} from "react-bootstrap";
import {FiSettings, FiShoppingCart} from "react-icons/all";
import {AuthContext} from "../../context/AuthContext";


const Navbar: React.FC = () => {
    const {toggleTheme} = useToggleTheme();
    const {signOut} = useContext(AuthContext)
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
            <button onClick={signOut}>Logout</button>
        </Container>

    );
}

export default Navbar;