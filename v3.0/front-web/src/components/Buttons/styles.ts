import styled from "styled-components";
import {darken} from 'polished';


export const Button = styled.button`
    color: white;
    transition: 0.3s;
    font-weight: 500;
    &:active{
        transform: scale(0.98);
    }
    
    &: focus{
         transition: 0.3s;
    }
`

export const PrimaryButton = styled(Button)`
     background: ${props => props.theme.colors.primary}!important;
     color: ${props => props.theme.colors.background}!important;
     &:hover{
         background: ${props => props && darken(0.1, props.theme.colors.primary)}!important;
          color: ${props => props.theme.colors.background}!important;
     }
     
     &:active{
         background: ${props => props && darken(0.12, props.theme.colors.primary)}!important;
     }
     
     &:focus{
          border-color: ${props => props && darken(0.12, props.theme.colors.primary)}!important;
          box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.025)!important;
     }
`

export const RedButton = styled(Button)`
     background: var(--red);
      color: ${props => props.theme.colors.background}!important;
     &:hover{
         background: ${darken(0.1, '#ff5555')}!important;
          color: ${props => props.theme.colors.background}!important;
     }
     
     &:active{
        background: ${darken(0.12, '#ff5555')}!important;
     }
     
     &:focus{
        background: ${darken(0.12, '#ff5555')}!important;
        box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.025)!important;
     }
`
export const CyanButton = styled(Button)`
     background: var(--cyan);
     color: var(--black);
     
     &:hover{
         background: ${darken(0.1, '#80ffea')}!important;
         color: var(--black);
     }
     
     &:active{
        background: ${darken(0.12, '#80ffea')}!important;
     }
     
     &:focus{
        background: ${darken(0.12, '#80ffea')}!important;
        box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.025)!important;
     }
`

export const YellowButton = styled(Button)`
     background: var(--yellow);
     color: var(--black);
     
     &:hover{
         background: ${darken(0.1, '#ffff80')}!important;
         color: var(--black);
     }
     
     &:active{
        background: ${darken(0.12, '#ffff80')}!important;
     }
     
     &:focus{
        background: ${darken(0.12, '#ffff80')}!important;
        box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.025)!important;
     }
`

export const GreenButton = styled(Button)`
     background: var(--green);
     color: var(--black);
     
     &:hover{
         background: ${darken(0.1, '#8aff80')}!important;
         color: var(--black);
     }
     
     &:active{
        background: ${darken(0.12, '#8aff80')}!important;
     }
     
     &:focus{
        background: ${darken(0.12, '#8aff80')}!important;
        box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.025)!important;
     }
`