import {CyanSecondaryButton, RedButton} from "./styles";
import {FiEdit2, FiTrash} from "react-icons/all";
import React from "react";
import {CircleLoader} from "../Loader";

interface Props extends React.ButtonHTMLAttributes<HTMLButtonElement>{
}

interface ButtonLoaderProps {
    Button: React.ComponentType<React.HTMLProps<HTMLButtonElement>>
    className?: string;
    type?: 'large' | 'normal'
}

export function EditButton({...props}: Props) {
    return (
        <CyanSecondaryButton className="btn btn-sm" {...props}><FiEdit2/></CyanSecondaryButton>
    )
}

export function DeleteButton({...props}: Props) {
    return (
        <RedButton className="btn btn-sm ms-2" {...props}><FiTrash/></RedButton>
    )
}

export function ButtonWithLoader({Button, className, type}: ButtonLoaderProps) {
    const buttonType = type === 'large' ? 'btn-lg' : '';
    const loaderSize = type === 'large' ? 'md' : 'sm';
    return(
        <Button className={'btn ' + className + ' ' + buttonType} disabled> <CircleLoader size={loaderSize}/> </Button>
    )
}