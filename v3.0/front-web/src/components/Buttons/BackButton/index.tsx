import {FiChevronLeft} from "react-icons/all";
import {Header} from "./styles";
import {Link} from 'react-router-dom'
import {useContext} from "react";
import {GeneralContext} from "../../../context/GeneralContex";

interface Props {
    onClick?: () => void,
    to: string
}
export default function BackButtonHeader({ to }: Props) {
    const {clearPreviousData} = useContext(GeneralContext);
    return (
        <Header className={'container '}>
            <div>&nbsp;</div>
            <Link to={to} onClick={clearPreviousData}>
                <FiChevronLeft size={17} />Voltar
            </Link>
        </Header>
    )
}