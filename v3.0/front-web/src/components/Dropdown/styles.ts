import styled from "styled-components";

export const ActionDropdownContent = styled.div `

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