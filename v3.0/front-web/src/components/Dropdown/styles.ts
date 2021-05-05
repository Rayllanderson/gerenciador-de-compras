import styled from "styled-components";
import {darken} from "polished";

export const DropdownContent = styled.div `
    .drop-header{
        display: flex;
        justify-content: center;
          color:  ${props => props.theme.colors.text}!important;
    }
    
    .drop-menu{
        background: ${props => props.theme.colors.background}!important;
        border: 1px solid ${props => props.theme.colors.primary}!important;
        a{
            color:  ${props => props.theme.colors.text}!important;
        }
        a:hover{
         background: ${props => props.theme.colors.primary}!important;
         color: white!important;
        }
    }
`

export const NavBarContent = styled(DropdownContent)`

     button{
      margin-left: 10px;
      background: transparent;
      color: ${props => props.theme.colors.primary}!important;
      border:none;
      
      box-shadow: none!important;
      
      &:hover{
         background: ${props => darken(0.1, props.theme.colors.primary)}!important;
         color: white!important;
      }
      
      &::after {
        display: none;
      }
    }
`
