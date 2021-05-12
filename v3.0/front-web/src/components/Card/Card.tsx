import {CardContainer} from './styles';
import {ReactNode} from "react";

interface Props {
    children: ReactNode;
}
export function Card ({children}: Props) {
    return (
       <CardContainer>
           {children}
       </CardContainer>
    )
}