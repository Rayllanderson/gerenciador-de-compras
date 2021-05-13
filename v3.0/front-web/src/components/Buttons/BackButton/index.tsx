import {AiOutlineArrowLeft, AiOutlineHome, FiShoppingCart} from "react-icons/all";
import {Header} from "./styles";
import React, {useCallback, useContext} from "react";
import {GeneralContext} from "../../../contexts/GeneralContex";
import {Link, useHistory} from "react-router-dom";

interface BackButtonNavbarProps {
    isOnHomePage: boolean
}

function BackButtonHeader() {
    const {clearPreviousData, clearPaginationSettings} = useContext(GeneralContext);
    let history = useHistory();

    const clearData = useCallback(() => {
        clearPreviousData();
        clearPaginationSettings();
    }, [clearPreviousData, clearPaginationSettings])

    const clearDataAndBack = useCallback(() => {
        clearData();
        history.goBack();
    }, [clearData, history]);

    return (
        <Header className={'container d-flex'}>
            <button onClick={clearDataAndBack} title={'Voltar'}>
                <AiOutlineArrowLeft size={22}/>
            </button>
            <Link to={'/categories'} onClick={clearData} className={'mx-3 d-flex'} title={'Ir para suas Listas'}>
                <AiOutlineHome size={22}/>
            </Link>
        </Header>
    )
}

export function BackButtonNavbar({isOnHomePage}: BackButtonNavbarProps) {

    return (
        isOnHomePage ? <Link to={'/home'} className={'link'}> <FiShoppingCart size={22}/> </Link> : <BackButtonHeader />
    )
}