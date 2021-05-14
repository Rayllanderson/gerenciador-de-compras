import React, {useEffect, useState} from "react";
import {Container} from './styles';
import ToggleSwitchTheme from "../Switch";
import useToggleTheme from "../../hooks/useToggleTheme";
import {DropdownNavbar} from "../Dropdown";
import {BackButtonNavbar} from "../Buttons/BackButton";


const Navbar: React.FC = () => {
    const {toggleTheme} = useToggleTheme();
   const [isOnHomePage, setIsOnHomePage] = useState(false);
   const [isOnCategoryPage, setIsOnCategoryPage] = useState(true);

    useEffect(() => {
        const currentPath = window.location.href;
        const HOME_PAGE_NAME = 'home';
        const CATEGORY_PAGE_NAME = 'categories';
        setIsOnHomePage(currentPath.includes(HOME_PAGE_NAME));
        setIsOnCategoryPage(currentPath.includes(CATEGORY_PAGE_NAME) && (!currentPath.includes(CATEGORY_PAGE_NAME + '/')))
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [setIsOnHomePage, window.location.href])

    return (
        <Container className="navbar ">
            <div className="container" style={{maxWidth: 750}}>
                <div className=" d-flex justify-content-start">
                    <BackButtonNavbar isOnHomePage={isOnHomePage} isOnCategoryPage={isOnCategoryPage}/>
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