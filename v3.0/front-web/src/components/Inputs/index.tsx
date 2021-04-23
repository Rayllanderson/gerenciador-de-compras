import {Form} from "react-bootstrap";
import {CheckboxContainer, Container} from './styles';
import React, {useCallback, useRef, useState} from "react";

interface Props {
    placeholder?: string;
    handleChange?: (e: any) => void,
    value?: string;
}

interface InputWithIconsProps extends Props {
    icon: React.ComponentType;
    required: boolean;
    type: string;
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

export function InputCheckbox({handleChange}: Props) {
    return (
        <CheckboxContainer>
            <Form.Check type="checkbox" className="checkbox" onChange={handleChange}/>
        </CheckboxContainer>
    )
}

export function InputWithIcon({handleChange, value, placeholder, icon: Icon, required, type}: InputWithIconsProps) {
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
                   onChange={handleChange}
                   value={value}
                   type={type}
                   placeholder={placeholder}
                   onFocus={() => setIsFocused(true)}
                   onBlur={handleInputBlur}
                   ref={inputRef} required={required}/>
        </Container>
    )
}
