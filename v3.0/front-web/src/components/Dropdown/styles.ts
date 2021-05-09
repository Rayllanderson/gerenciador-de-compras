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
interface NavBarContentProps {
    hasImage: boolean
}
export const NavBarContent = styled(DropdownContent)<NavBarContentProps>`
     button{
      margin-left: 10px;
      background: transparent;
      color: ${props => props.theme.colors.primary}!important;
      transition: .2s;
      border:none;
      border-radius: 5px;
      
      box-shadow: none!important;
      
      ${props => props.hasImage && 'padding: .30rem;'}
      
      &:hover{
         background: ${props => darken(0.1, props.theme.colors.primary)}!important;
         color: white!important;
         ${props => props.hasImage && 'border-radius: 50%; opacity: 0.9;'}
      }
      
      &::after {
        display: none;
      }
    }
`

export const DropdownNavbarImage = styled.img `
    border-radius: 50%;
`
