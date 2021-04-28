import {Form} from "react-bootstrap";
import {CheckboxContainer, Container} from './styles';
import React, {useCallback, useRef, useState} from "react";
// @ts-ignore
import CurrencyInput from 'react-currency-input';

interface Props {
    placeholder?: string;
    onChange?: (e: any) => void,
    value?: string,
    onClick?: (e: any) => void,
}

interface CheckboxProps extends Props {
    checked?: boolean;
}

interface InputWithIconsProps extends Props {
    icon: React.ComponentType;
    required: boolean;
    type: string;
}

export function InputText({placeholder, value, onChange}: Props) {
    return (
        <input type={'text'} placeholder={placeholder}
               className="form-control form-control-lg" onChange={onChange} value={value}/>
    )
}

export function InputNumber({value, onChange}: Props) {
    return (
        <CurrencyInput value={value} decimalSeparator="," thousandSeparator="."
                       onChangeEvent={onChange} className={'form-control form-control-lg'}/>
    )
}


export function InputCheckbox({onChange, value, checked, onClick}: CheckboxProps) {
    return (
        <CheckboxContainer>
            <Form.Check type="checkbox" className="checkbox" onChange={onChange} value={value}
                        checked={checked} onClick={onClick}/>
        </CheckboxContainer>
    )
}

export function InputWithIcon({onChange, value, placeholder, icon: Icon, required, type}: InputWithIconsProps) {
    const inputRef = useRef<HTMLInputElement>(null);
    const [isFocused, setIsFocused] = useState(false)
    const [isFilled, setIsFilled] = useState(false)

    const handleInputBlur = useCallback(() => {
        setIsFocused(false);
        const hasValue = !!inputRef.current?.value;
        setIsFilled(hasValue)
    }, []);

    return (
        <Container className="input-group" isFocused={isFocused} isFilled={isFilled}>
            <span className="input-group-text">
                 <Icon/>
            </span>
            <input className="form-control form-control-lg inner-addon left-addon"
                   onChange={onChange}
                   value={value}
                   type={type}
                   placeholder={placeholder}
                   onFocus={() => setIsFocused(true)}
                   onBlur={handleInputBlur}
                   ref={inputRef} required={required}/>
        </Container>
    )
}
