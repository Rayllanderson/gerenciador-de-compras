import styled from "styled-components";
import {darken} from "polished";
import {cyan} from "../../utils/colorsUtil";

export const Summary = styled.div`
    text-align: left;
    padding: 5rem;
 `

export const SummaryHeader = styled.h5 `
   color: ${cyan};
`

export const SummaryItem = styled.a`
   display: block;
   color: ${props => props.theme.colors.primary};
   text-decoration: underline;
   cursor: pointer;
   padding-left: 12px;
   &:hover{
    color: ${props => props && darken(0.1, props.theme.colors.primary)};
   }
`