import {ReactNode} from "react";
import {PrimaryButton} from '../styles';

interface Props {
    disabled?: boolean;
    onClick?(e: any): void;
    children: ReactNode;
}

export function Button({disabled, children, onClick }: Props) {
    return (
        <PrimaryButton disabled={disabled}
                       type="button"
                       onClick={onClick}
                       className={`btn btn-lg`}>
            {children}
        </PrimaryButton>
    );
}