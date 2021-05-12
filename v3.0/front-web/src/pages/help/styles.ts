import styled from "styled-components";
import {darken} from "polished";

export const Summary = styled.div`
    text-align: left;
    padding-bottom: 1rem;
    padding-left: 1rem;
 `

export const SummaryHeader = styled.h5`
`

export const SummaryItem = styled.a`
   display: block;
   color: ${props => props.theme.colors.primary};
   text-decoration: underline;
   cursor: pointer;
   text-indent: 12px;
   &:hover{
    color: ${props => props && darken(0.1, props.theme.colors.primary)};
   }
`

export const HelpContent = styled.div`
   text-align: left!important;
   padding-left: 1rem;
   padding-bottom: 2rem;
   h5{
    text-align: left!important;
   } 
   
   p{
     text-indent: 20px;
   }
`
export const HelpContentHeader = styled.h5`
  color: ${props => props.theme.colors.primary}!important;
  margin-top: 2rem;
`

export const Image = styled.img`
   display: block;
   margin: 0 auto;
`