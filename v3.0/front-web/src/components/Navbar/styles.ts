import styled from "styled-components";

export const Container = styled.div `
    width: 100%;
    background: ${props => props.theme.colors.backgroundSecondary};
    color: ${props => props.theme.colors.primary};
    box-shadow: 0px 1px 10px 1px rgba(0,0,0,0.08);   
    
   div{
    align-items: center;
    }
    
    button{
      margin-left: 10px;
      background: transparent;
      color: var(--white);
      border:none;
      &:hover{
          background: var(--blackSecondary)!important;
          color: var(--white);
      }
      &:active {
          background: var(--blackSecondary)!important;
          color: var(--white);
      }
      &:focus{
          box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.025), 0 0 8px rgba(255, 121, 198, 0.07);
      }
      &::after {
        display: none;
      }
    }

      
`