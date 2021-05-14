import {AiOutlineArrowLeft, AiOutlineHome, FiShoppingCart} from "react-icons/all";
import {Header} from "./styles";
import React, {useCallback, useContext} from "react";
import {GeneralContext} from "../../../contexts/GeneralContex";
import {Link, useHistory} from "react-router-dom";

interface BackButtonNavbarProps {
    isOnHomePage: boolean
    isOnCategoryPage: boolean
}


export function BackButtonNavbar({isOnHomePage, isOnCategoryPage}: BackButtonNavbarProps) {
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
            {isOnHomePage ?
                <Link to={'/categories'} className={'link'} title={'Ir para suas Listas'}>
                    <FiShoppingCart size={22}/>
                </Link>
                :
                isOnCategoryPage ?
                    <Link to={'/home'} className={' d-flex'} title={'Home'}>
                        <AiOutlineHome size={22}/>
                    </Link>
                    : (
                        <>
                            <button onClick={clearDataAndBack} title={'Voltar'}>
                                <AiOutlineArrowLeft size={22}/>
                            </button>
                            <Link to={'/categories'} onClick={clearData} className={'mx-3 d-flex'} title={'Home'}>
                                <FiShoppingCart size={22}/>
                            </Link>
                        </>
                    )
            }
        </Header>

    )
}