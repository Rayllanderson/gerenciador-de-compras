import styled from "styled-components";


export const Container = styled.div`
    button{
        background: transparent;
        border: none!important;
        color: ${props => props.theme.colors.primary};
        cursor: pointer;
        box-shadow: none!important;
    }
`
