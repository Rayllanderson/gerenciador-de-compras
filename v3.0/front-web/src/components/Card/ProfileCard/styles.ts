import styled from "styled-components";
import {darken, lighten} from "polished";

interface Props{
    color: string;
}

export const Label = styled.label<Props> `
    color: ${props => props.color};
    display: block;
    text-align: center;
    cursor: pointer;
    transition: .2s;
    border-radius: 5px;
    &:hover{
        color: ${props => props && darken(0.12, props.color)};
        background: ${props => props.theme.title === 'light' ? darken(0.04, props.theme.colors.backgroundSecondary)
        : lighten(0.03, props.theme.colors.backgroundSecondary) };
        color:  ${props => props.theme.title === 'light' ? lighten(0.04,  props.color)
        : darken(0.03, props.color)};
    }
`