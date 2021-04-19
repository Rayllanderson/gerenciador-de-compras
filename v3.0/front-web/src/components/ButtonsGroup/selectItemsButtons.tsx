import {SelectButtonsContainer} from './styles'
import {FiX} from "react-icons/all";
import {ReactNode} from "react";

interface Props {
    children: ReactNode;
}
export function SelectItemsButtons({children}: Props) {
    return (
        <SelectButtonsContainer className={'container mb-4'}>
            <button
                className="position-absolute top-0 start-100 translate-middle badge close-card">
                <FiX size={23}/>
            </button>

            <div>
                <p className={'text-center'}> 3 selecionados</p>
            </div>
            <div className={"buttons"}>
                {children}
            </div>
        </SelectButtonsContainer>
    )
}