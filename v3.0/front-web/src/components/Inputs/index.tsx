import {Form} from "react-bootstrap";
import {CheckboxContainer} from './styles';
import {useState} from "react";

interface Props {
    placeholder?: string;
    handleOnChange?: (e: any) => void,
}

export function InputText({placeholder}: Props) {
    return (
        <input type={'text'} placeholder={placeholder}
               className="form-control form-control-lg"/>
    )
}

export function InputNumber({placeholder}: Props) {
    return (
        <input type={'text'} placeholder={placeholder}
               className="form-control form-control-lg"/>
    )
}

export function InputCheckbox({handleOnChange}: Props) {
    return (
        <CheckboxContainer>
            <Form.Check type="checkbox" className="checkbox" onChange={handleOnChange}/>
        </CheckboxContainer>
    )
}
