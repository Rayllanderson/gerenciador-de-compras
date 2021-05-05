import {FiChevronLeft} from "react-icons/all";
import {Header} from "./styles";
import {Link} from 'react-router-dom'
import {useCallback, useContext} from "react";
import {GeneralContext} from "../../../contexts/GeneralContex";

interface Props {
    onClick?: () => void,
    to: string
}
export default function BackButtonHeader({ to }: Props) {
    const {clearPreviousData, clearPaginationSettings} = useContext(GeneralContext);
    const clearData = useCallback(() => {
        clearPreviousData();
        clearPaginationSettings();
    }, [clearPreviousData, clearPaginationSettings])
    return (
        <Header className={'container '}>
            <div>&nbsp;</div>
            <Link to={to} onClick={clearData}>
                <FiChevronLeft size={17} />Voltar
            </Link>
        </Header>
    )
}