import styled from "styled-components";

export const Container = styled.div `
    max-width: 750px;
    margin-top: 1.5rem;
    
    .group-buttons {
        display: flex;
        justify-content: flex-end; 
    }
    
    .button-dropdown {
        &:after {
        display: none;
      }
    }
`

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
        }
    }
`