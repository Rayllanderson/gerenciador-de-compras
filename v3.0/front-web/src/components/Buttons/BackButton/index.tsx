import {FiChevronLeft, FiShoppingCart} from "react-icons/all";
import {Header} from "./styles";
import React, {useCallback, useContext} from "react";
import {GeneralContext} from "../../../contexts/GeneralContex";
import { useHistory } from "react-router-dom";

interface BackButtonNavbarProps {
    isOnHomePage: boolean
}

function BackButtonHeader() {
    const {clearPreviousData, clearPaginationSettings} = useContext(GeneralContext);
    let history = useHistory();
    const clearDataAndBack = useCallback(() => {
        clearPreviousData();
        clearPaginationSettings();
        history.goBack();
    }, [clearPreviousData, clearPaginationSettings])
    return (
        <Header className={'container '}>
            <button onClick={clearDataAndBack}>
                <FiChevronLeft size={17}/>Voltar
            </button>
        </Header>
    )
}

export function BackButtonNavbar({isOnHomePage}: BackButtonNavbarProps) {

    return (
        isOnHomePage ? <FiShoppingCart size={22}/> : <BackButtonHeader />
    )
}