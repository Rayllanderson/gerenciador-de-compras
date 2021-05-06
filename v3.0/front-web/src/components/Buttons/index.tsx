import {CyanSecondaryButton, RedButton} from "./styles";
import {FiEdit2, FiTrash} from "react-icons/all";
import React from "react";

interface Props extends React.ButtonHTMLAttributes<HTMLButtonElement>{
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
