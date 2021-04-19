import {FiChevronLeft} from "react-icons/all";
import {Header} from "./styles";

interface Props {
    onClick?: () => void;
}
export default function BackButtonHeader({ onClick }: Props) {
    return (
        <Header className={'container'}>
            <div>&nbsp;</div>
            <a onClick={onClick} href={'#d'}>
                <FiChevronLeft size={17} />Voltar
            </a>
        </Header>
    )
}