import styled from "styled-components";
import {darken, lighten} from 'polished';


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
     &:hover{
         background: ${props => props && darken(0.1, props.theme.colors.primary)}!important;
          color: white!important;
     }
     
     &:active{
         background: ${props => props && darken(0.12, props.theme.colors.primary)}!important;
     }
     
     &:focus{
          border-color: ${props => props && darken(0.12, props.theme.colors.primary)}!important;
          box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.025)!important;
     }
`
export const SecondaryButton = styled(Button)`
     background: ${props => props.theme.colors.backgroundSecondary}!important;
     color: ${props => props.theme.colors.text}!important;
     &:hover{
         background: ${props => props && darken(0.1, props.theme.colors.backgroundSecondary)}!important;
     }
     
     &:active{
         background: ${props => props && darken(0.12, props.theme.colors.backgroundSecondary)}!important;
     }
     
     &:focus{
          border-color: ${props => props && darken(0.12, props.theme.colors.backgroundSecondary)}!important;
          box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.025)!important;
     }
`



export const RedButton = styled(Button)`
     background: #e83f5b;
     &:hover{
         background: ${darken(0.1, '#e83f5b')}!important;
         color: var(--white);
     }
     
     &:active{
        background: ${darken(0.12, '#e83f5b')}!important;
     }
     
     &:focus{
        background: ${darken(0.12, '#e83f5b')}!important;
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
     background: #f7df1e;
     color: var(--black);
     
     &:hover{
         background: ${darken(0.1, '#f7df1e')}!important;
         color: var(--black);
     }
     
     &:active{
        background: ${darken(0.12, '#f7df1e')}!important;
     }
     
     &:focus{
        background: ${darken(0.12, '#f7df1e')}!important;
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

const cyan = '#00b4d8';
export const CyanSecondaryButton = styled(Button)`
      
     background: cyan;
     color: var(--black);
     
     &:hover{
         background: ${lighten(0.1, cyan)}!important;
     }
     
     &:active{
        background: ${lighten(0.12, cyan)}!important;
     }
     
     &:focus{
        background: ${lighten(0.12, cyan)}!important;
        box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.025)!important;
     }
`
