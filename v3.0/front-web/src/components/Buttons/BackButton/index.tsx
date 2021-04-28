import {FiChevronLeft} from "react-icons/all";
import {Header} from "./styles";
import {Link} from 'react-router-dom'

interface Props {
    onClick?: () => void,
    to: string
}
export default function BackButtonHeader({ to }: Props) {
    return (
        <Header className={'container'}>
            <div>&nbsp;</div>
            <Link to={to}>
                <FiChevronLeft size={17} />Voltar
            </Link>
        </Header>
    )
}